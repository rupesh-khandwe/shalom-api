package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/category")
    public List<Category> getCategory() {
        return eventService.findAllCategory();
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveEvent(@RequestBody Event event) throws Exception {
        try{
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
