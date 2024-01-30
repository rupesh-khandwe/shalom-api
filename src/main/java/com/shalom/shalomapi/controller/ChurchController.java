package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.Church;
import com.shalom.shalomapi.service.ChurchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/church/v1")
public class ChurchController {

    @Autowired
    private ChurchService churchService;

    @GetMapping("/churchById")
    public Church getChurchById(@RequestParam(name = "id", defaultValue = "1") String id){
        System.out.println(id);
        return churchService.findById(Long.parseLong(id));
    }

    @GetMapping("/churchList")
    public List<Church> getBible(){
        return churchService.findAll();
    }

    @GetMapping("/searchByKey")
    public List<Church> getChurchBySearchKey(@RequestParam(name = "key", defaultValue = "Bengaluru") String key){
        System.out.println(key);
        return churchService.findChurchBySearchKey(key);
    }

    @PostMapping("/register")
    public ResponseEntity<?> postChurch(@RequestBody Church church) throws Exception {
        try{
            churchService.registerChurch(church);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
