package com.example.recomendationservice.controller;

import com.example.recomendationservice.dto.CoordinatesDepartmentDto;
import com.example.recomendationservice.dto.FilterDto;
import com.example.recomendationservice.service.DispatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final DispatchService dispatchService;

    @GetMapping("/offices/{latitude}/{longitude}/{countOffices}/{time}")
    public ResponseEntity<List<CoordinatesDepartmentDto>>
    getPrioritizedOffices(@PathVariable double latitude,
                          @PathVariable double longitude,
                          @PathVariable int countOffices,
                          @PathVariable String time,
                          @RequestBody(required = false) FilterDto filterDto){

        return ResponseEntity.ok()
                .body(dispatchService
                        .getPrioritizedOffices(
                                latitude, longitude, countOffices, time, filterDto
                        )
                );
    }
}
