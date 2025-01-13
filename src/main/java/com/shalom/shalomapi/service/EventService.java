package com.shalom.shalomapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.shalom.shalomapi.dto.EventDTO;
import com.shalom.shalomapi.model.Category;
import com.shalom.shalomapi.model.Event;
import com.shalom.shalomapi.model.IEvent;
import com.shalom.shalomapi.model.IEventNotify;
import com.shalom.shalomapi.repository.CategoryRepository;
import com.shalom.shalomapi.repository.EventRepository;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private DocumentService docService;

    @Autowired
    private Utils utils;

    public IEvent findById(Long id){
        return eventRepo.findByEventId(id);
    }

    public List<IEvent> findByUserId(Long userId){
        System.out.println("findAllEvents ***** ");
        return eventRepo.findAllEvents();
    }

    public List<IEventNotify> findNotificationByUserId(Long userId){
        return eventRepo.findNotificationByUserId(userId);
    }

    public List<Category> findAllCategory(){
        return categoryRepo.findAll();
    }

    public Event save(EventDTO eventDto){
        //return eventRepo.save(event);
        Event event = new Event();
        if(eventDto.getEventId()!=null){
            event.setEventId(eventDto.getEventId());
        }
        //2024-11-10T00:15:00.000Z
        //DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        //DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
        //LocalDate date = LocalDate.parse("2018-04-10T04:00:00.000Z", inputFormatter);
        //String formattedDate = outputFormatter.format(date);
        //System.out.println(formattedDate); // prints 10-04-2018
        event.setUserId(eventDto.getUserId());
        event.setCategoryId(eventDto.getCategoryId());
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        //String inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        //String outputFormat = "dd/MM/yyyy";
        System.out.println("UI event date "+ eventDto.getEventDate());
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        //LocalDate date = LocalDate.parse(eventDto.getEventDate(), inputFormatter);
        //String outputDate = date.format(DateTimeFormatter.ofPattern(outputFormat));
        LocalDate date = LocalDate.parse(eventDto.getEventDate(), inputFormatter);
        String formattedDate = outputFormatter.format(date); //date.plusDays(1)
        System.out.println("API event date "+ formattedDate); // prints 10-04-2018
        event.setEventDate(formattedDate);
        event.setEventTime(eventDto.getEventTime());
        event.setPhone1(eventDto.getPhone1());
        event.setPhone2(eventDto.getPhone2());
        event.setAddressline1(eventDto.getAddressline1());
        event.setAddressline2(eventDto.getAddressline2());
        event.setCountryId(eventDto.getCountryId());
        event.setStateId(eventDto.getStateId());
        event.setCityId(eventDto.getCityId());
        event.setRegionId(eventDto.getRegionId());
        event.setCreatedBy(eventDto.getCreatedBy());
        event.setCreatedOn(eventDto.getCreatedOn());
        event.setUpdatedOn(eventDto.getUpdatedOn());
        event.setLanguageId(eventDto.getLanguageId());
        String imageSeparator = "|";

        //event.setImageUrl();
        Event newEvent = eventRepo.saveAndFlush(event);

        int i=1;
         String s3FilePath = "";
        for(String imageUrl : eventDto.getImageUrl()){

            Document doc = Jsoup.parse(imageUrl);
            //System.out.println("parsed image"+ doc.body().text().split("base64,"));

            String[] baseImage = doc.body().text().split("base64,");
            // Note preferred way of declaring an array variable
            String[] mimeType = baseImage[0].split(":");
            String fileMimeType= mimeType.length>1?mimeType[1].substring(0, mimeType[1].length() - 1):"image/jpeg";
            String[] fileExt = fileMimeType.split("/");
            String fileExtType= fileExt.length>1?fileExt[1]:"jpeg";
            String s3FileName = utils.generatingRandomAlphanumericString("event/"+Long.toString(newEvent.getEventId()), fileExtType);
            byte[] data = DatatypeConverter.parseBase64Binary(baseImage[1]);
            InputStream stream = new ByteArrayInputStream(data);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(data.length);
            meta.setContentType(fileMimeType);
            s3FilePath += docService.upload(stream, meta, s3FileName,eventDto.getImageUrl().length, i);
            i++;
        }
        eventRepo.updateImageUrl(newEvent.getEventId() ,s3FilePath);
        return newEvent;
    }

    public void deleteEvent(Long id){
        eventRepo.deleteById(id);
    }
}
