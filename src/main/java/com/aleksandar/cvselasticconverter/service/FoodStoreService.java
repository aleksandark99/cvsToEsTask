package com.aleksandar.cvselasticconverter.service;

import com.aleksandar.cvselasticconverter.model.dto.FoodStore;
import com.aleksandar.cvselasticconverter.model.es.FoodStoreDoc;

import java.util.List;

public interface FoodStoreService {

    List<FoodStoreDoc> findFoodStoresByName(String name);

    FoodStoreDoc findClosestFoodStore(double latitude, double longitude);

}
