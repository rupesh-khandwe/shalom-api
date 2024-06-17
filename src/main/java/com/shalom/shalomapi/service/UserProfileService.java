package com.shalom.shalomapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.repository.UserProfileRepository;
import org.apache.commons.text.WordUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserProfileService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private DocumentService docService;

    @Autowired
    private Utils utils;

/*
    @Autowired
    private PasswordEncoder bcryptEncoder;
*/
    public void saveUserProfile(UserProfile userProfile) {
        //userProfile.setPassword(bcryptEncoder.encode(userProfile.getPassword()));
        UserProfile userProf = new UserProfile();
        userProf.setUserName(userProfile.getUserName());
        userProf.setPassword(new BCryptPasswordEncoder().encode(userProfile.getPassword()));
        userProf.setEmail(userProfile.getEmail());
        userProf.setFirstName(WordUtils.capitalizeFully(userProfile.getFirstName()));
        //userProf.setFirstName(WordUtils.capitalizeFully(userProfile.getMiddleName()));
        userProf.setLastName(WordUtils.capitalizeFully(userProfile.getLastName()));
        userProf.setGender(userProfile.getGender());
        userProf.setPhone1(userProfile.getPhone1());
        userProf.setPhone2(userProfile.getPhone2());
        userProf.setAddressLine1(userProfile.getAddressLine1());
        userProf.setAddressLine2(userProfile.getAddressLine2());
        //userProf.setCountryId(userProfile.getCountryId());
        userProf.setCountryId(Long.parseLong("78"));
        userProf.setStateId(userProfile.getStateId());
        userProf.setCityId(userProfile.getCityId());
        userProf.setRegionId(userProfile.getRegionId());
        userProf.setCreatedOn((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        userProfileRepo.save(userProf);
    }

   /* public UserDetails getUserByNameAndPassword(String userName, String password) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepo.findByUserNameAndPassword(userName, password);
        if(userProfile == null){
            throw new UsernameNotFoundException("Invalid user-id and password");
        } else {
            return new User("rupesh", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        }
    }*/

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepo.findByUserName(username);
        if(userProfile == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
//            return new org.springframework.security.core.userdetails.User(userProfile.getUserName(), userProfile.getPassword(),
//                    new ArrayList<>());
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            return new CustomUser(userProfile.getUserName(),
                    userProfile.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    new ArrayList<>(),
                    userProfile.getUserId(),
                    userProfile.getFirstName(),
                    userProfile.getLastName()
                    );
        }
    }

    public IEditUserProfile findUserProfile(Long userId){
        String isNotNull = userProfileRepo.findUserProfileByUserIdAndNullCondition(userId);
        System.out.println("Flag value"+isNotNull);
        if(null!=isNotNull){
            System.out.println("true value");
            return userProfileRepo.findUserProfileByUserId(userId);
        } else {
            System.out.println("false value");
            return userProfileRepo.findUserProfileByUserIdAndStateIdIsNull(userId);
        }
    }

    public void saveOrUpdateUserProfile(UserProfile userProfile) {
        try {
            userProfileRepo.saveOrUpdate(userProfile.getUserId(), userProfile.getEmail(), userProfile.getFirstName(), userProfile.getMiddleName(), userProfile.getLastName(),
                    userProfile.getPhone1(), userProfile.getPhone2(), userProfile.getAddressLine1(), userProfile.getAddressLine2(), userProfile.getStateId(),
                    userProfile.getCityId(), userProfile.getRegionId());
        } catch(ConstraintViolationException ex){
            System.out.println(ex.getStackTrace());
        }
    }

    public List<IUser> getUsers(Long userId, Boolean followFlag){
        return userProfileRepo.getUsers(userId, followFlag);
    }

    public void updateUserProfilePic(Long userId, String profilePic) {
        try {
                Document doc = Jsoup.parse(profilePic);
                //Element div = doc.body();
                //Elements img = doc.select("img");
                //System.out.println("parsed html"+ div.text());
                System.out.println("parsed image"+ doc.body().text().split("base64,"));

               // Elements elements = doc.getElementsByTag("img");
                String s3FilePath = "";
                int i=1;
               // for (Element element : elements) {
                  //  if (element.attr("src").startsWith("data:image")) {
                       // System.out.println(element.attr("src"));
                        String[] baseImage = doc.body().text().split("base64,");
                        System.out.println(baseImage[0]);
                        // Note preferred way of declaring an array variable
                        String[] mimeType = baseImage[0].split(":"); //data:image/jpeg
                        String fileMimeType= mimeType.length>1?mimeType[1].substring(0, mimeType[1].length() - 1):"image/jpeg";
                       // System.out.println(fileMimeType+elements.size());
                        String[] fileExt = fileMimeType.split("/");
                        String fileExtType= fileExt.length>1?fileExt[1]:"jpeg";
                        String s3FileName = utils.generatingRandomAlphanumericString(userId.toString()+"/profile", fileExtType);

                        if(baseImage.length>=1){
                            byte[] data = DatatypeConverter.parseBase64Binary(baseImage[1]);
                            InputStream stream = new ByteArrayInputStream(data);
                            ObjectMetadata meta = new ObjectMetadata();
                            meta.setContentLength(data.length);
                            meta.setContentType(fileMimeType);
                            s3FilePath += docService.upload(stream, meta, s3FileName,0, i);
                            //i++;
                        }
                  //  }
              //  }

            userProfileRepo.updateUserProfilePic(userId, s3FilePath);
        } catch(ConstraintViolationException ex){
            System.out.println(ex.getStackTrace());
        } catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }

    public String findProfilePic(Long userId){
        return userProfileRepo.findImageUrlByUserId(userId);
    }
}
