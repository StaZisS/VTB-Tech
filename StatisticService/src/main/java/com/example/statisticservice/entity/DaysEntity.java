package com.example.statisticservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "days")
public class DaysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String day;

    @OneToMany(mappedBy = "daysEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HourEntity> hour;

    @ManyToOne
    @JoinColumn(name = "statistic_id")
    @JsonIgnore
    private StatisticEntity statisticEntity;
}
