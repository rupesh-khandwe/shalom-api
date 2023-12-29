package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.Bible;
import com.shalom.shalomapi.service.BibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bible/v1")
public class BibleController {

    @Autowired
    private BibleService bibleService;

    @GetMapping("/bibleList/{id}")
    public Bible getBibleById(@RequestParam(value = "id", defaultValue = "1") String id){
        System.out.println(id);
        return bibleService.findById(Long.parseLong(id));
    }

    @GetMapping("/bibleList")
    public List<Bible> getBible(){
        return bibleService.findAll();
    }
}
