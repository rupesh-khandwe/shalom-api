package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.Event;
import com.shalom.shalomapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/eventById")
    public Event getEventById(@RequestParam(name = "id", defaultValue = "1") String id){
        return eventService.findById(Long.parseLong(id));
    }

    @GetMapping("/eventByUserId")
    public List<Event> getEventByUserId(@RequestParam(name = "id", defaultValue = "1") String id) {
        return eventService.findByUserId(Long.parseLong(id));
    }

//    @GetMapping("/church/v1/searchByKey")
//    public List<Church> getChurchBySearchKey(@RequestParam(name = "key", defaultValue = "Bengaluru") String key){
//        return eventService.findChurchBySearchKey(key);
//    }
}
