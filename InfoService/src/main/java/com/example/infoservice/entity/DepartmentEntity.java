package com.example.infoservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String salePointName;
    private String address;
    private String status;

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpenHours> openHours;

    private String rko;

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpenHoursIndividual> openHoursIndividual;

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

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceDepartment> services;
}
