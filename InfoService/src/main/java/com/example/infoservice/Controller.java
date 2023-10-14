package com.example.infoservice;

import com.example.infoservice.dto.CoordinatesDepartmentDto;
import com.example.infoservice.dto.FilterDto;
import com.example.infoservice.entity.AtmEntity;
import com.example.infoservice.entity.DepartmentEntity;
import com.example.infoservice.repository.AtmRepo;
import com.example.infoservice.repository.DepartmentRepo;
import com.example.infoservice.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/info_service")
@RequiredArgsConstructor
public class Controller {
    private final DepartmentRepo departmentRepo;
    private final AtmRepo atmRepo;
    private final FilterService filterService;

    @GetMapping ("/offices")
    public ResponseEntity<List<DepartmentEntity>> getDepartments(@RequestBody(required = false) FilterDto filterDto){
        return ResponseEntity.ok(filterService.filterDepartment(filterDto));
    }

    @PostMapping("/offices/coordinates")
    public ResponseEntity<List<CoordinatesDepartmentDto>> getDepartmentsCoordinates(@RequestBody(required = false) FilterDto filterDto){
        return ResponseEntity.ok(
                filterService.filterDepartment(filterDto).stream().map(
                departmentEntity ->
                        CoordinatesDepartmentDto.builder()
                        .latitude(departmentEntity.getLatitude())
                        .longitude(departmentEntity.getLongitude())
                        .id(departmentEntity.getId())
                        .build()
            ).toList()
        );
    }

    @GetMapping ("/offices/{id}")
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
