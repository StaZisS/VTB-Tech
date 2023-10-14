package com.example.infoservice.repository;

import com.example.infoservice.entity.AtmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AtmRepo extends JpaRepository<AtmEntity, UUID> {
}
