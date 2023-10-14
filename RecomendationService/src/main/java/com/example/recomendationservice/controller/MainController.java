package com.example.recomendationservice.controller;

import com.example.recomendationservice.dto.CoordinatesDto;
import com.example.recomendationservice.dto.FilterAtmsDto;
import com.example.recomendationservice.dto.FilterDepartmentsDto;
import com.example.recomendationservice.service.DispatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final DispatchService dispatchService;

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
