package com.example.recomendationservice.controller;

import com.example.recomendationservice.dto.CoordinatesDto;
import com.example.recomendationservice.dto.FilterAtmsDto;
import com.example.recomendationservice.dto.FilterDepartmentsDto;
import com.example.recomendationservice.service.DispatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MainController", description = "Controller for dispatching")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final DispatchService dispatchService;

    @Operation(summary = "Получить список рекомендованных офисов")
    @GetMapping("/offices/{latitude}/{longitude}/{countOffices}/{time}")
    public ResponseEntity<List<CoordinatesDto>>
    getPrioritizedOffices(@PathVariable double latitude,
                          @PathVariable double longitude,
                          @PathVariable int countOffices,
                          @PathVariable String time,
                          @RequestBody(required = false) FilterDepartmentsDto filterDepartmentsDto){
        return ResponseEntity.ok()
                .body(dispatchService
                        .getPrioritizedOffices(
                                latitude, longitude, countOffices, time, filterDepartmentsDto
                        )
                );
    }

    @Operation(summary = "Получить список рекомендованных банкоматов")
    @GetMapping("/atms/{latitude}/{longitude}/{countAtms}/{time}")
    public ResponseEntity<List<CoordinatesDto>>
            getPrioritizedAtms(@PathVariable double latitude,
                                @PathVariable double longitude,
                                @PathVariable int countAtms,
                                @PathVariable String time,
                                @RequestBody(required = false) FilterAtmsDto filterAtmsDto){
        return ResponseEntity.ok()
                .body(dispatchService
                        .getPrioritizedAtms(
                                latitude, longitude, countAtms, time, filterAtmsDto
                        )
                );
    }
}
