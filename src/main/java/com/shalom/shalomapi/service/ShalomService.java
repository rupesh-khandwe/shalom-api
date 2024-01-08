package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.Shalom;
import com.shalom.shalomapi.model.ShalomLike;
import com.shalom.shalomapi.repository.ShalomLikeRepository;
import com.shalom.shalomapi.repository.ShalomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShalomService {

    @Autowired
    private ShalomRepository shalomRepo;

    @Autowired
    private ShalomLikeRepository shalomLikeRepo;

    public Shalom findById(Long id){
        Shalom shalomValue = shalomRepo.findByShalomId(id);
//        Church churchValue = constants.orElseThrow(() ->
//                new RuntimeException("No such data found"));
        return shalomValue;
    }

    public List<Shalom> findByUserId(Long userId){
        return shalomRepo.findByUserId(userId);
    }

//    public List<Shalom> findAll(){
//        return shalomRepo.findAll();
//    }

    public List<Shalom> findAllLikeComment(){
        return shalomRepo.findAllLikeComment();
    }

    public Shalom saveShalom(Shalom shalom){
        return shalomRepo.save(shalom);
    }

    public void updateLike(Long userId, Long shalomId, Boolean flag){
        shalomLikeRepo.updateLike(userId, shalomId, flag);
    }
}
