package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.Church;
import com.shalom.shalomapi.repository.ChurchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ChurchService {

    @Autowired
    private ChurchRepository churchRepo;

    public Church findById(Long id){
        Church churchValue = churchRepo.findByChurchId(id);
//        Church churchValue = constants.orElseThrow(() ->
//                new RuntimeException("No such data found"));
        return churchValue;
    }

    public List<Church> findAll(){
        return churchRepo.findAll();
    }

    public List<Church> findChurchBySearchKey(String key){
         return churchRepo.findByChurchNameContainingOrAddressline1ContainingOrAddressline2ContainingCaseInsensitive(key.toLowerCase());
    }

    public void registerChurch(Church church) {
        //church.setPassword(bcryptEncoder.encode(church.getPassword()));
//        Church newChurch = new Church();
//        newChurch.setUserId(church.getUserId());
//        newChurch.setChurchName(church.getChurchName());
//        newChurch.setChurchWebsiteUrl(church.getChurchWebsiteUrl());
//        newChurch.setPhone1(church.getPhone1());
//        newChurch.setPhone2(church.getPhone2());
//        newChurch.setAddressline1(church.getAddressline1());
//        newChurch.setAddressline2(church.getAddressline2());
//        newChurch.setCountryId(church.getCountryId());
//        newChurch.setStateId(church.getStateId());
//        newChurch.setCityId(church.getCityId());
//        newChurch.setRegionId(church.getRegionId());
//        newChurch.setCreatedBy(church.getCreatedBy());
//        newChurch.setCreatedOn((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        churchRepo.save(church);
    }

}
