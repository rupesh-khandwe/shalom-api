package com.shalom.shalomapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.repository.ShalomCommentRepository;
import com.shalom.shalomapi.repository.ShalomLikeRepository;
import com.shalom.shalomapi.repository.ShalomRepository;
import com.shalom.shalomapi.repository.UserFollowRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.time.ZoneId;
import java.util.*;

@Service
public class ShalomService {

    @Autowired
    private ShalomRepository shalomRepo;

    @Autowired
    private DocumentService docService;

    @Autowired
    private ShalomLikeRepository shalomLikeRepo;

    @Autowired
    private ShalomCommentRepository shalomCommentRepo;

    @Autowired
    private UserFollowRepository userFollowRepo;

    public Shalom findById(Long id){
        Shalom shalomValue = shalomRepo.findByShalomId(id);
//        Church churchValue = constants.orElseThrow(() ->
//                new RuntimeException("No such data found"));
        return shalomValue;
    }

    public List<Shalom> findByUserId(Long userId){
        return shalomRepo.findByUserId(userId);
    }

    public List<ShalomComment> findCommentByShalomId(Long shalomId){
        return shalomCommentRepo.findByShalomId(shalomId);
    }

    public ShalomComment saveShalomComment(ShalomComment shalomComment){
        return shalomCommentRepo.save(shalomComment);
    }

    public List<IShalomLikeComment> findAllLikeComment(Long userId){
        return shalomRepo.findAllLikeComment(userId);
    }

    public Shalom saveShalom(ShalomDTO shalomDto){

        Shalom shalom = new Shalom();
        Document doc = Jsoup.parse(shalomDto.getShalom());
        Element div = doc.body().child(0);
        Elements img = doc.select("img");
        System.out.println("parsed html"+ div.text());
        System.out.println("parsed image"+ img);

        Elements elements = doc.getElementsByTag("img");
        String s3FilePath = "";
        for (Element element : elements) {
            if (element.attr("src").startsWith("data:image")) {
                System.out.println(element.attr("src"));
                String[] baseImage = element.attr("src").toString().split("base64,");
                System.out.println(baseImage[0]);
                // Note preferred way of declaring an array variable
                String[] mimeType = baseImage[0].split(":"); //data:image/jpeg
                String fileMimeType= mimeType.length>1?mimeType[1].substring(0, mimeType[1].length() - 1):"image/jpeg";
                System.out.println(fileMimeType);
                String[] fileExt = fileMimeType.split("/");
                String fileExtType= fileExt.length>1?fileExt[1]:"jpeg";
                String s3FileName = generatingRandomAlphanumericString(Long.toString(shalomDto.getUserId()), fileExtType);

                if(baseImage.length>1){
                    byte[] data = DatatypeConverter.parseBase64Binary(baseImage[1]);
                    InputStream stream = new ByteArrayInputStream(data);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(data.length);
                    meta.setContentType(fileMimeType);
                    s3FilePath += docService.upload(stream, meta, s3FileName);

/*                    try (OutputStream fstream = new FileOutputStream("C:\\Users\\Ghost\\OneDrive\\shalom\\"+s3FileName)) {
                        fstream.write(data);
                    } catch (FileNotFoundException ex){
                        System.out.println(ex.getStackTrace());
                    } catch (IOException ex){
                        System.out.println(ex.getStackTrace());
                    } catch(Exception ex){
                        System.out.println(ex.getStackTrace());
                    }*/
                }
            }
        }

        shalom.setImageUrl(s3FilePath);
        shalom.setShalom(div.text());
        shalom.setShalomFlag(true);
        shalom.setUserId(shalomDto.getUserId());
        shalom.setUserName(shalomDto.getUserName());
        shalom.setCreatedOn((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return shalomRepo.save(shalom);
    }

    public void updateLike(Long userId, Long shalomId, Boolean flag){
        ShalomLike shalomLike = new ShalomLike();
        Long id = shalomLikeRepo.findByUserIdAndShalomId(userId, shalomId);
        if(Objects.isNull(id)){
            shalomLike.setShalomId(shalomId);
            shalomLike.setUserId(userId);
            shalomLike.setLikeFlag(true);
            shalomLike.setCreatedOn((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            shalomLikeRepo.save(shalomLike);
        }else {
            shalomLikeRepo.updateLike(userId, shalomId, flag);
        }
    }

    public String generatingRandomAlphanumericString(String userId, String fileExt) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return userId+"/"+generatedString+"."+fileExt.trim();
    }

    public List<IUserFollow> findFollowers(Long followId, Boolean flag){
        return userFollowRepo.findByFollowId(followId, flag);
    }

    public List<IUserFollow> findFollowings(Long userId, Boolean flag){
        return userFollowRepo.findByUserId(userId, flag);
    }

    public void saveOrUpdateFollower(UserFollow userFollow){
        if(userFollowRepo.findByUserIdAndFollowId(userFollow.getUserId(), userFollow.getFollowId())!=null){
            userFollowRepo.updateFollowFlag(userFollow.getUserId(), userFollow.getFollowId(), userFollow.getFollowFlag());
        } else{
            userFollowRepo.save(userFollow);
        }

    }

    public IUserProfile findProfileCountsByUserId(Long userId){
        return userFollowRepo.findCountByUserId(userId);
    }
}
