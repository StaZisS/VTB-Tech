package com.example.infoservice;

import com.example.infoservice.dto.CoordinatesDto;
import com.example.infoservice.dto.FilterAtmsDto;
import com.example.infoservice.dto.FilterDepartmentDto;
import com.example.infoservice.entity.AtmEntity;
import com.example.infoservice.entity.DepartmentEntity;
import com.example.infoservice.repository.AtmRepo;
import com.example.infoservice.repository.DepartmentRepo;
import com.example.infoservice.service.FilterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@Tag(name = "InfoServiceController", description = "Controller for info service")
@RequestMapping("/info_service")
@RequiredArgsConstructor
public class Controller {
    private final DepartmentRepo departmentRepo;
    private final AtmRepo atmRepo;
    private final FilterService filterService;

    @Operation(summary = "Получить список всех отделений")
    @GetMapping ("/offices")
    public ResponseEntity<List<DepartmentEntity>> getDepartments(@RequestBody(required = false) FilterDepartmentDto filterDepartmentDto){
        return ResponseEntity.ok(filterService.filterDepartment(filterDepartmentDto));
    }


    @Operation(summary = "Получить данные для фильтрации отделений")
    @PostMapping("/offices/coordinates")
    public ResponseEntity<List<CoordinatesDto>>
    getDepartmentsCoordinates(@RequestBody(required = false) FilterDepartmentDto filterDepartmentDto){
        return ResponseEntity.ok(
                filterService.filterDepartment(filterDepartmentDto).stream().map(
                departmentEntity ->
                        CoordinatesDto.builder()
                        .latitude(departmentEntity.getLatitude())
                        .longitude(departmentEntity.getLongitude())
                        .id(departmentEntity.getId())
                        .build()
            ).toList()
        );
    }


    @Operation(summary = "Получить отделение по id")
    @GetMapping ("/offices/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable UUID id) {
        Optional<DepartmentEntity> department = departmentRepo.findById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить список всех банкоматов")
    @GetMapping("/atms")
    public ResponseEntity<List<AtmEntity>> getAtms(){
        return ResponseEntity.ok(atmRepo.findAll());
    }

    @Operation(summary = "Получить данные для фильтрации банкоматов")
    @PostMapping("/atms/coordinates")
    public ResponseEntity<List<CoordinatesDto>>
    getAtmsCoordinates(@RequestBody(required = false) FilterAtmsDto filterAtmsDto) {
        return ResponseEntity.ok(
                filterService.filterAtm(filterAtmsDto).stream().map(
                        atmEntity ->
                                CoordinatesDto.builder()
                                        .latitude(atmEntity.getLatitude())
                                        .longitude(atmEntity.getLongitude())
                                        .id(atmEntity.getId())
                                        .build()
                ).toList()
        );
    }


    @Operation(summary = "Получить банкомат по id")
    @GetMapping("/atms/{id}")
    public ResponseEntity<AtmEntity> getAtmById(@PathVariable UUID id) {
        Optional<AtmEntity> atm = atmRepo.findById(id);
        return atm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
