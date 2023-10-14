package com.example.infoservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service_department")
public class ServiceDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceDepartmentEnum serviceDepartmentEnum;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private DepartmentEntity departmentEntity;
}
