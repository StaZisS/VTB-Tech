package com.example.mobiletech.controller;

import com.example.mobiletech.entity.Office;
import com.example.mobiletech.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/nearest-office")
    public List<Office> getNearestOffice(@RequestParam("latitude") Double latitude,
                                         @RequestParam("longitude") Double longitude,
                                         @RequestParam(value = "numberOfOffices", defaultValue = "3") int numberOfOffices) {
        return officeService.findNearestOffices(latitude, longitude, numberOfOffices);
    }
}
