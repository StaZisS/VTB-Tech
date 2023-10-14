package com.example.statisticservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statistic")
public class StatisticEntity {
    @Id
    private UUID idDepartment;

    @OneToMany(mappedBy = "statisticEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DaysEntity> days;
}
