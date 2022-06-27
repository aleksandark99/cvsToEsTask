package com.aleksandar.cvselasticconverter.repository;

import com.aleksandar.cvselasticconverter.model.es.FoodStoreDoc;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodStoreRepository extends ElasticsearchRepository<FoodStoreDoc,Integer> {

        FoodStoreDoc searchTop1By(Sort sort);

        List<FoodStoreDoc> findByEntityNameContainsOrDbaNameContainsOrCityContainsOrStreetNameContains(String entityName,String dbaName,String city,String street);
}
