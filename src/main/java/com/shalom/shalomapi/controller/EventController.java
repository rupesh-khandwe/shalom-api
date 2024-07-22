package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.dto.EventDTO;
import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/id")
    public IEvent getEventById(@RequestParam(name = "id", defaultValue = "1") String id){
        return eventService.findById(Long.parseLong(id));
    }

    @GetMapping("/user")
    public List<IEvent> getEventByUserId(@RequestParam(name = "id", defaultValue = "1") String id) {
        return eventService.findByUserId(Long.parseLong(id));
    }

    @GetMapping("/category")
    public List<Category> getCategory() {
        return eventService.findAllCategory();
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveEvent(@RequestBody EventDTO event) throws Exception {
        try{
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete")
    public List<IEvent> deleteEvent(@RequestParam(name = "id") String id, @RequestParam(name = "userId") String userId){
        eventService.deleteEvent(Long.parseLong(id));
        return eventService.findByUserId(Long.parseLong(userId));
    }

    @GetMapping("/notification")
    public List<IEventNotify> getEventNotification(@RequestParam(name = "id", defaultValue = "1") String id) {
        return eventService.findNotificationByUserId(Long.parseLong(id));
    }

}
