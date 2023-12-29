package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.Church;
import com.shalom.shalomapi.repository.ChurchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
