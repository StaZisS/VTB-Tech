package com.example.infoservice;

import com.example.infoservice.entity.AtmEntity;
import com.example.infoservice.entity.DepartmentEntity;
import com.example.infoservice.repository.AtmRepo;
import com.example.infoservice.repository.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/info_service")
@RequiredArgsConstructor
public class Controller {
    private final DepartmentRepo departmentRepo;
    private final AtmRepo atmRepo;

    @GetMapping ("/departments")
    public ResponseEntity<List<DepartmentEntity>> getDepartments(){
        var departments = departmentRepo.findAll();
        return ResponseEntity.ok(departments.subList(0, departments.size()/4));
    }

    @GetMapping ("/departments/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable UUID id) {
        Optional<DepartmentEntity> department = departmentRepo.findById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/atms")
    public ResponseEntity<List<AtmEntity>> getAtms(){
        return ResponseEntity.ok(atmRepo.findAll());
    }

    @GetMapping("/atms/{id}")
    public ResponseEntity<AtmEntity> getAtmById(@PathVariable UUID id) {
        Optional<AtmEntity> atm = atmRepo.findById(id);
        return atm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
