package com.example.statisticservice.repository;

import com.example.statisticservice.entity.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatisticRepo extends JpaRepository<StatisticEntity, UUID> {


}
