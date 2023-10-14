package com.example.mobiletech;

import com.example.mobiletech.entity.Office;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OfficeRepository extends Neo4jRepository<Office, Long> {
}
