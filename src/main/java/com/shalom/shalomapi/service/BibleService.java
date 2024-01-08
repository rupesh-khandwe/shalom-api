package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.Bible;
import com.shalom.shalomapi.repository.BibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibleService {

    @Autowired
    private BibleRepository bibleRepo;

    public Bible findById(Long id){
        Optional<Bible> constants = bibleRepo.findById(id);
        Bible value = constants.orElseThrow(() ->
                new RuntimeException("No such data found"));
        return value;
    }

    public List<Bible> findAll(){
        return bibleRepo.findAll();
    }
}
