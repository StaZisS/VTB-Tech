package com.example.mobiletech.entity;


import jdk.jfr.Label;
import jdk.jfr.Name;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

@Node("Office")
@Data
public class Office {
    @Id
    @GeneratedValue
    private Long id;
    @Property("name")
    private String salePointName;
    private String address;
    private String status;
    private List<OpenHours> openHours;
    private String rko;
    private List<OpenHours> openHoursIndividual;
    private String officeType;
    private String salePointFormat;
    private String suoAvailability;
    private String hasRamp;
    private Double latitude;
    private Double longitude;
    private String metroStation;
    private Integer distance;
    private Boolean kep;
    private Boolean myBranch;

}

