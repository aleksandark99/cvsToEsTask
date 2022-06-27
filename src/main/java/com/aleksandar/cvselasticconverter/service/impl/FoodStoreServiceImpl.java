package com.aleksandar.cvselasticconverter.service.impl;

import com.aleksandar.cvselasticconverter.model.es.FoodStoreDoc;
import com.aleksandar.cvselasticconverter.repository.FoodStoreRepository;
import com.aleksandar.cvselasticconverter.service.FoodStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodStoreServiceImpl implements FoodStoreService {

    private FoodStoreRepository foodStoreRepository;

    @Autowired
    public FoodStoreServiceImpl(FoodStoreRepository foodStoreRepository){
        this.foodStoreRepository = foodStoreRepository;
    }

    @Override
    public List<FoodStoreDoc> findFoodStoresByName(String name) {
        return foodStoreRepository.findByEntityNameContainsOrDbaNameContainsOrCityContainsOrStreetNameContains(name,name,name,name);
    }

    @Override
    public FoodStoreDoc findClosestFoodStore(double latitude, double longitude) {
        GeoPoint location = new GeoPoint(latitude,longitude);
        Sort sort = Sort.by(new GeoDistanceOrder("location", location).withUnit("km"));
        return foodStoreRepository.searchTop1By(sort);
    }

}
