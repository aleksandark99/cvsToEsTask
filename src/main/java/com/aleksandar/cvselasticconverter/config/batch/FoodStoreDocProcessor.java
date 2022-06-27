package com.aleksandar.cvselasticconverter.config.batch;

import com.aleksandar.cvselasticconverter.model.dto.FoodStore;
import com.aleksandar.cvselasticconverter.model.es.FoodStoreDoc;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;
@Component
public class FoodStoreDocProcessor implements ItemProcessor<FoodStore, FoodStoreDoc> {

    @Override
    public FoodStoreDoc process(FoodStore foodStore) throws Exception {

        GeoPoint geoPoint = null;
        if(foodStore.getLongitude() != null && foodStore.getLatitude() != null){
            geoPoint = new GeoPoint(foodStore.getLatitude(),foodStore.getLongitude());
        }

        return  FoodStoreDoc.builder()
                .location(geoPoint)
                .city(foodStore.getCity())
                .country(foodStore.getCountry())
                .dbaName(foodStore.getDbaName())
                .entityName(foodStore.getEntityName())
                .establishmentType(foodStore.getEstablishmentType())
                .licenseNumber(foodStore.getLicenseNumber())
                .squareFootage(foodStore.getSquareFootage())
                .stateAbbreviation(foodStore.getStateAbbreviation())
                .streetName(foodStore.getStreetName())
                .streetNumber(foodStore.getStreetNumber())
                .zipCode(foodStore.getZipCode())
                .build();
    }
}
