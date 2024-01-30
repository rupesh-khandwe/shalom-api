package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.Category;
import com.shalom.shalomapi.model.Event;
import com.shalom.shalomapi.repository.CategoryRepository;
import com.shalom.shalomapi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    public Event findById(Long id){
        Event eventValue = eventRepo.findByEventId(id);
//        Church churchValue = constants.orElseThrow(() ->
//                new RuntimeException("No such data found"));
        return eventValue;
    }

    public List<Event> findByUserId(Long userId){
        return eventRepo.findByUserId(userId);
    }

    public List<Category> findAllCategory(){
        return categoryRepo.findAll();
    }

//    public List<Church> findChurchBySearchKey(String key){
//         return churchRepo.findByChurchNameContainingOrAddressline1ContainingOrAddressline2ContainingCaseInsensitive(key.toLowerCase());
//    }

    public Event save(Event event){
        return eventRepo.save(event);
    }
}
