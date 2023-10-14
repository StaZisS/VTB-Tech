package com.example.statisticservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hourly_service_count")
public class HourlyServiceCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceDepartmentEnum serviceType;
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "hour_entry_id")
    @JsonIgnore
    private HourEntity hourEntity;
}