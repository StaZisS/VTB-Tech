package com.example.infoservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "open_hours")
public class OpenHours {
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
