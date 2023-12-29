package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.City;
import com.shalom.shalomapi.model.Country;
import com.shalom.shalomapi.model.Region;
import com.shalom.shalomapi.model.State;
import com.shalom.shalomapi.repository.CityRepository;
import com.shalom.shalomapi.repository.CountryRepository;
import com.shalom.shalomapi.repository.RegionRepository;
import com.shalom.shalomapi.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private CountryRepository countryRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private RegionRepository regionRepo;

    public List<Country> findAllCountry(){
        return countryRepo.findAllByOrderByCountryNameAsc();
    }

    public List<State> findStateByCountryId(Long countryId){
        List<State> stateList = stateRepo.findAllByCountryId(countryId);
        return stateList;
    }

    public List<City> findCityByStateAndCountryId(Long stateId, Long countryId){
        List<City> cityList = cityRepo.findAllByStateIdAndCountryId(stateId, countryId);
        return cityList;
    }

    public List<Region> findRegionByCityId(Long cityId){
        List<Region> regionList = regionRepo.findAllByCityId(cityId);
        return regionList;
    }
}
