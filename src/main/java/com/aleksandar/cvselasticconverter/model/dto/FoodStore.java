package com.aleksandar.cvselasticconverter.model.dto;

import lombok.Data;

@Data
public class FoodStore {

    private String country;
    private int licenseNumber;
    private String establishmentType; //Should ideally be Enum
    private String entityName;
    private String dbaName;
    private String streetNumber;//some contain '-' or could contain letters '11A' so I went with String
    private String streetName;
    private String city;
    private String stateAbbreviation;
    private int zipCode;
    private double squareFootage;
    private Double latitude;
    private Double longitude;
}
