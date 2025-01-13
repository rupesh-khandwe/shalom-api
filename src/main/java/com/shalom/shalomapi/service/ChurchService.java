package com.shalom.shalomapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.shalom.shalomapi.dto.ChurchDTO;
import com.shalom.shalomapi.model.Church;
import com.shalom.shalomapi.model.IChurch;
import com.shalom.shalomapi.model.Language;
import com.shalom.shalomapi.repository.ChurchRepository;
import com.shalom.shalomapi.repository.LanguageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ChurchService {

    @Autowired
    private ChurchRepository churchRepo;

    @Autowired
    private DocumentService docService;

    @Autowired
    private Utils utils;

    @Autowired
    private LanguageRepository languageRepo;

    public Church findById(Long id){
        Church churchValue = churchRepo.findByChurchId(id);
//        Church churchValue = constants.orElseThrow(() ->
//                new RuntimeException("No such data found"));
        return churchValue;
    }

    public List<Church> findAll(){
        return churchRepo.findAllByCreatedOn();
    }

    public List<IChurch> findChurchBySearchKey(){

         return churchRepo.findByChurchNameContainingOrAddressline1ContainingOrAddressline2ContainingCaseInsensitive();
    }

    public void registerChurch(ChurchDTO church) {
        //church.setPassword(bcryptEncoder.encode(church.getPassword()));
        Church newChurch = new Church();
        if(church.getChurchId()!=null){
            System.out.println("church is there ");
            newChurch.setChurchId(church.getChurchId());
        }
        newChurch.setUserId(church.getUserId());
        newChurch.setChurchName(church.getChurchName());
        newChurch.setChurchWebsiteUrl(church.getChurchWebsiteUrl());
        newChurch.setAboutChurch(church.getAboutChurch());
        newChurch.setPhone1(church.getPhone1());
        newChurch.setPhone2(church.getPhone2());
        newChurch.setAddressline1(church.getAddressline1());
        newChurch.setAddressline2(church.getAddressline2());
        newChurch.setCountryId(church.getCountryId());
        newChurch.setStateId(church.getStateId());
        newChurch.setCityId(church.getCityId());
        newChurch.setRegionId(null);
        newChurch.setCreatedBy(church.getCreatedBy());
        newChurch.setLanguageId(church.getLanguageId());
        newChurch.setCreatedOn((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        Church registeredChurch = churchRepo.saveAndFlush(newChurch);

        int i=1;
        String s3FilePath = "";
        for(String imageUrl : church.getImageUrl()){

            Document doc = Jsoup.parse(imageUrl);
            System.out.println("parsed image"+ doc.body().text().split("base64,"));

            String[] baseImage = doc.body().text().split("base64,");
            // Note preferred way of declaring an array variable
            String[] mimeType = baseImage[0].split(":");
            String fileMimeType= mimeType.length>1?mimeType[1].substring(0, mimeType[1].length() - 1):"image/jpeg";
            String[] fileExt = fileMimeType.split("/");
            String fileExtType= fileExt.length>1?fileExt[1]:"jpeg";
            String s3FileName = utils.generatingRandomAlphanumericString("church/"+Long.toString(registeredChurch.getChurchId()), fileExtType);
            byte[] data = DatatypeConverter.parseBase64Binary(baseImage[1]);
            InputStream stream = new ByteArrayInputStream(data);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(data.length);
            meta.setContentType(fileMimeType);
            s3FilePath += docService.upload(stream, meta, s3FileName,church.getImageUrl().length, i);
            i++;
        }
        churchRepo.updateImageUrl(registeredChurch.getChurchId() ,s3FilePath);

    }

    public void deleteChurch(Long id){
        churchRepo.deleteById(id);
    }

    public List<Language> getAllLanguage(){
        System.out.println("getAllLanguage ***** ");
        return languageRepo.findAll();
    }
}
