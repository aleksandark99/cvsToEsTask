package com.aleksandar.cvselasticconverter.controller;

import com.aleksandar.cvselasticconverter.api.FoodStoresApi;
import com.aleksandar.cvselasticconverter.dto.FoodStore;
import com.aleksandar.cvselasticconverter.model.utils.ObjectMapperUtils;
import com.aleksandar.cvselasticconverter.service.impl.FoodStoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class FoodStoreController implements FoodStoresApi {

    @Autowired
    private FoodStoreServiceImpl foodStoreService;

    private ObjectMapperUtils mapper;

    @Override
    public ResponseEntity<FoodStore> getTheClosestFoodStore(Double latitude, Double longitude) {
        return ResponseEntity.ok(mapper.map(foodStoreService.findClosestFoodStore(latitude, longitude), FoodStore.class));
    }

    @Override
    public ResponseEntity<List<FoodStore>> getTheClosestFoodStoreByNameOrAddress(String nameOrAddress) {
        return  ResponseEntity.ok(mapper.mapAll(foodStoreService.findFoodStoresByName(nameOrAddress), FoodStore.class));
    }

}
