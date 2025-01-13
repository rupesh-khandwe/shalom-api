package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.dto.ShalomUniversalDTO;
import com.shalom.shalomapi.dto.UserProfileDTO;
import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.service.ShalomService;
import com.shalom.shalomapi.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shalom")
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

    @GetMapping("/user")
    public List<ShalomUniversalDTO> getShalomByUserId(@RequestParam(name = "id", defaultValue = "1") String id) {
        return shalomService.findByUserId(Long.parseLong(id));
    }


    @GetMapping("/comment")
    public List<IShalomComment> getCommentByShalomId (@RequestParam(name = "id", defaultValue = "1") String id) {
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
    public List<ShalomUniversalDTO> getAllLikeComment(@RequestParam(name = "userId") String userId){
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
    public List<ShalomUniversalDTO> updateLike(@RequestParam(name = "userId") String userId, @RequestParam(name = "shalomId") String shalomId, @RequestParam(name = "likeFlag") String likeFlag){
        shalomService.updateLike(Long.parseLong(userId), Long.parseLong(shalomId), Boolean.parseBoolean(likeFlag));
        return shalomService.findAllLikeComment(Long.parseLong(userId));
    }

    @GetMapping("/followers")
    public List<IUserFollow> getFollowers(@RequestParam(name = "followId") String followId, @RequestParam(name = "followFlag", defaultValue = "true") String followFlag) {
        return shalomService.findFollowers(Long.parseLong(followId), Boolean.parseBoolean(followFlag));
    }

    @GetMapping("/following")
    public List<IUserFollow> getFollowings(@RequestParam(name = "userId") String userId, @RequestParam(name = "followFlag", defaultValue = "true")String followFlag) {
        return shalomService.findFollowings(Long.parseLong(userId), Boolean.parseBoolean(followFlag));
    }

    @PutMapping("/saveFollow")
    public ResponseEntity<List<IUser>> updateFollow(@RequestBody UserFollow userFollow){
        try{
            shalomService.saveOrUpdateFollower(userFollow);
            return new ResponseEntity<>(userProfileService.getUsers(userFollow.getUserId(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/updateFollowing")
    public ResponseEntity<List<IUserFollow>> updateFollowing(@RequestBody UserFollow userFollow){
        try{
            shalomService.saveOrUpdateFollower(userFollow);
            return new ResponseEntity<>(shalomService.findFollowings(userFollow.getUserId(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/updateFollower")
    public ResponseEntity<List<IUserFollow>> updateFollower(@RequestBody UserFollow userFollow){
        try{
            shalomService.saveOrUpdateFollower(userFollow);
            return new ResponseEntity<>(shalomService.findFollowers(userFollow.getFollowId(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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

    @PutMapping("/profilepic")
    public ResponseEntity<String> updateProfilePic(@RequestBody UserProfileDTO userProfileDTO) throws Exception {
        try {
            String profilePicPath = userProfileService.updateUserProfilePic(userProfileDTO.getUserId(), userProfileDTO.getProfilePic());
            return new ResponseEntity<String>(profilePicPath,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<IUser>> getUsers(@RequestParam(name = "userId") String userId){
        try {
            return new ResponseEntity<>(userProfileService.getUsers(Long.parseLong(userId), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete")
    public List<ShalomUniversalDTO> deleteShalom(@RequestParam(name = "id") String id, @RequestParam(name = "userId") String userId){
        shalomService.deleteShalom(Long.parseLong(id));
        return shalomService.findByUserId(Long.parseLong(userId));
    }

    @GetMapping("/profilePic")
    public String findProfilePic(@RequestParam(name="userId") String userId){
        try{
            return userProfileService.findProfilePic(Long.parseLong(userId));
        } catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
            return "ERROR";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "ERROR";
        }
    }

    @PostMapping("/donate")
    public ResponseEntity<String> saveDonation(@RequestBody Donation donation){
        shalomService.saveDonation(donation);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
