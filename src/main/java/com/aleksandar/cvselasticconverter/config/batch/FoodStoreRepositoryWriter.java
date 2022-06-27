package com.aleksandar.cvselasticconverter.config.batch;

import com.aleksandar.cvselasticconverter.model.es.FoodStoreDoc;
import com.aleksandar.cvselasticconverter.repository.FoodStoreRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FoodStoreRepositoryWriter<T> implements ItemWriter<FoodStoreDoc> {

    @Autowired
    private FoodStoreRepository foodStoreRepository;

    @Override
    public void write(List<? extends FoodStoreDoc> items) throws Exception {
        foodStoreRepository.saveAll(items);
    }
}
