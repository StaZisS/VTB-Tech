package com.example.infoservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "open_hours_individual")
public class OpenHoursIndividual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String days;
    private String hours;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private DepartmentEntity departmentEntity;
}
