package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.Bible;
import com.shalom.shalomapi.model.Shalom;
import com.shalom.shalomapi.model.ShalomLike;
import com.shalom.shalomapi.service.ShalomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shalom/v1")
public class ShalomController {

    @Autowired
    private ShalomService shalomService;

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
    public List<Shalom> getAllLikeComment(){
        return shalomService.findAllLikeComment();
    }

    @PostMapping("/saveShalom")
    public Shalom createLikeOnComment(Shalom shalom){
        return shalomService.saveShalom(shalom);
    }

    @PutMapping("/updateLike")
    public void updateLike(@RequestParam(name = "userId") String userId, @RequestParam(name = "shalomId") String shalomId, @RequestParam(name = "likeFlag") String likeFlag){
        shalomService.updateLike(Long.parseLong(userId), Long.parseLong(shalomId), Boolean.parseBoolean(likeFlag));
    }
}
