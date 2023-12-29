package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.City;
import com.shalom.shalomapi.model.Country;
import com.shalom.shalomapi.model.Region;
import com.shalom.shalomapi.model.State;
import com.shalom.shalomapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address/v1")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/CountryList")
    public List<Country> getCountryList(){
        return addressService.findAllCountry();
    }

    @GetMapping("/StateList")
    public List<State> getStateByCountryId(@RequestParam(value = "countryId", defaultValue = "78") String countryId){
        return addressService.findStateByCountryId(Long.parseLong(countryId));
    }

    @GetMapping("/CityList")
    public List<City> getCityByStateAndCountryId(@RequestParam(value = "stateId", defaultValue = "11") String stateId, @RequestParam(value = "countryId", defaultValue = "78")String countryId){
        return addressService.findCityByStateAndCountryId(Long.parseLong(stateId), Long.parseLong(countryId));
    }

    @GetMapping("/RegionList")
    public List<Region> getRegionByCityId(@RequestParam(value = "cityId", defaultValue = "228") String cityId){
        return addressService.findRegionByCityId(Long.parseLong(cityId));
    }


}
