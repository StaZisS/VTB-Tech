package com.example.statisticservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hour_entry")
public class HourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private int hour;

    @OneToMany(mappedBy = "hourEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HourlyServiceCount> hourlyServiceCount;

    @ManyToOne
    @JoinColumn(name = "days_id")
    @JsonIgnore
    private DaysEntity daysEntity;
}
