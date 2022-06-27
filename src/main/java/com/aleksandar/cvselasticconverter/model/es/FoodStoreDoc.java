package com.aleksandar.cvselasticconverter.model.es;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
@Document(indexName = "test")
@Data
@Builder
public class FoodStoreDoc {


    @Field(type = FieldType.Text)
    private String country;

    @Id
    @Field(type = FieldType.Integer)
    private int licenseNumber;

    @Field(type = FieldType.Text)
    private String establishmentType; //Should ideally be Enum

    @Field(type = FieldType.Text)
    private String entityName;

    @Field(type = FieldType.Text)
    private String dbaName;

    @Field(type = FieldType.Text)
    private String streetNumber;//some contain '-' or could contain letters '11A' so I went with String

    @Field(type = FieldType.Text)
    private String streetName;

    @Field(type = FieldType.Text)
    private String city;

    @Field(type = FieldType.Text)
    private String stateAbbreviation;

    @Field(type = FieldType.Integer)
    private int zipCode;

    @Field(type = FieldType.Double)
    private double squareFootage;

    @GeoPointField
    @Field(type = FieldType.Object)
    private GeoPoint location;

}
