package com.example.infoservice.repository;

import com.example.infoservice.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepo extends JpaRepository<DepartmentEntity, UUID> {
}
