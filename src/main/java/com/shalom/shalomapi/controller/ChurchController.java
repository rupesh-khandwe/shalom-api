package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.Church;
import com.shalom.shalomapi.model.IChurch;
import com.shalom.shalomapi.service.ChurchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.PrinterGraphics;
import java.util.List;

@RestController
@RequestMapping("/api/v1/church/")
public class ChurchController {

    @Autowired
    private ChurchService churchService;

    @GetMapping("/churchById")
    public Church getChurchById(@RequestParam(name = "id", defaultValue = "1") String id){
        System.out.println(id);
        return churchService.findById(Long.parseLong(id));
    }

    @GetMapping("/churchList")
    public List<Church> getChurch(){
        return churchService.findAll();
    }

    @GetMapping("/searchByKey")
    public List<IChurch> getChurchBySearchKey(@RequestParam(name = "key", defaultValue = "Bengaluru") String key){
        Long cityId = Long.parseLong("228");
        return churchService.findChurchBySearchKey(key, cityId);
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

    @DeleteMapping("/delete")
    public List<IChurch> deleteChurch(@RequestParam(name = "id") String id){
        churchService.deleteChurch(Long.parseLong(id));
        Long cityId = Long.parseLong("228");
        return churchService.findChurchBySearchKey("Bengaluru",cityId);
    }

}
