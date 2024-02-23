package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.service.ShalomService;
import com.shalom.shalomapi.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shalom/v1")
public class ShalomController {

    @Autowired
    private ShalomService shalomService;

    @Autowired
    private UserProfileService userProfileService;

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


    @GetMapping("/comment")
    public List<ShalomComment> getCommentByShalomId (@RequestParam(name = "id", defaultValue = "1") String id) {
        return shalomService.findCommentByShalomId(Long.parseLong(id));
    }

    @PostMapping("/save/comment")
    public ShalomComment saveShalom(@RequestBody ShalomComment shalomComment){
        return shalomService.saveShalomComment(shalomComment);
    }


//    @GetMapping("/shalom/v1/shaloms")
//    public List<Shalom> getAllShalom(){
//        return shalomService.findAll();
//    }

    @GetMapping("/shalomsWithLikeComment")
    public List<IShalomLikeComment> getAllLikeComment(@RequestParam(name = "userId") String userId){
        return shalomService.findAllLikeComment(Long.parseLong(userId));
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
        return shalomService.findAllLikeComment(Long.parseLong(userId));
    }

    @GetMapping("/followers")
    public List<IUserFollow> getFollowers(@RequestParam(name = "userId") String userId, @RequestParam(name = "followFlag", defaultValue = "true") String followFlag) {
        return shalomService.findFollowers(Long.parseLong(userId), Boolean.parseBoolean(followFlag));
    }

    @GetMapping("/following")
    public List<IUserFollow> getFollowings(@RequestParam(name = "followId") String followId, @RequestParam(name = "followFlag", defaultValue = "true")String followFlag) {
        return shalomService.findFollowings(Long.parseLong(followId), Boolean.parseBoolean(followFlag));
    }

    @PutMapping("/saveFollow")
    public List<IUserFollow> updateFollow(@RequestParam(name = "userId") String userId, @RequestParam(name = "followId") String followId, @RequestParam(name = "followFlag", defaultValue = "true") String followFlag){
        shalomService.updateFollowFlag(Long.parseLong(userId), Long.parseLong(followId), Boolean.parseBoolean(followFlag));
        return shalomService.findFollowers(Long.parseLong(userId), Boolean.parseBoolean(followFlag));
    }

    @GetMapping("/profile")
    public IUserProfile getCountsForProfile(@RequestParam(name = "userId") String userId) {
        return shalomService.findProfileCountsByUserId(Long.parseLong(userId));
    }

    @GetMapping("/profileEdit")
    public IEditUserProfile getUserProfile(@RequestParam(name = "userId") String userId){
        return userProfileService.findUserProfile(Long.parseLong(userId));
    }

    @PutMapping("/profileUpdate")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfile userProfile) throws Exception {
        try {
            userProfileService.saveOrUpdateUserProfile(userProfile);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<IUser>> getUsers(@RequestParam(name = "userId") String userId){
        try {
            return new ResponseEntity<>(userProfileService.getUsers(Long.parseLong(userId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    private ShalomLikeCommentDTO convertToDto(Shalom shalom) {
        ShalomLikeCommentDTO shalomDto = modelMapper.map(shalom, ShalomLikeCommentDTO.class);
        return shalomDto;
    }
}
