package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.service.ShalomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shalom/v1")
public class ShalomController {

    @Autowired
    private ShalomService shalomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/shalomById")
    public Shalom getShalomById(@RequestParam(name = "id", defaultValue = "1") String id){
        return shalomService.findById(Long.parseLong(id));
    }

    @GetMapping("/shalomByUserId")
    public List<Shalom> getShalomByUserId(@RequestParam(name = "id", defaultValue = "1") String id) {
        return shalomService.findByUserId(Long.parseLong(id));
    }

//    @GetMapping("/shalom/v1/shaloms")
//    public List<Shalom> getAllShalom(){
//        return shalomService.findAll();
//    }

    @GetMapping("/shalomsWithLikeComment")
    public List<IShalomLikeComment> getAllLikeComment(){
        return shalomService.findAllLikeComment();
//        List<Shalom> shaloms = shalomService.findAllLikeComment();
//        return shaloms.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public Shalom saveShalom(@RequestBody ShalomDTO shalom){
        return shalomService.saveShalom(shalom);
    }

    @PutMapping("/saveLike")
    public List<IShalomLikeComment> updateLike(@RequestParam(name = "userId") String userId, @RequestParam(name = "shalomId") String shalomId, @RequestParam(name = "likeFlag") String likeFlag){
        shalomService.updateLike(Long.parseLong(userId), Long.parseLong(shalomId), Boolean.parseBoolean(likeFlag));
        return shalomService.findAllLikeComment();
    }

    private ShalomLikeCommentDTO convertToDto(Shalom shalom) {
        ShalomLikeCommentDTO shalomDto = modelMapper.map(shalom, ShalomLikeCommentDTO.class);
        return shalomDto;
    }
}
