package com.example.mobiletech.controller;

import com.example.mobiletech.entity.Office;
import com.example.mobiletech.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/nearest-office")
    public Office getNearestOffice(@RequestParam("latitude") Double latitude,
                                   @RequestParam("longitude") Double longitude) {
        return officeService.findNearestOffice(latitude, longitude);
    }
}
