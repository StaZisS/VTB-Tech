package com.example.mobiletech.service;

import com.example.mobiletech.OfficeRepository;
import com.example.mobiletech.entity.Office;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Office findNearestOffice(Double latitude, Double longitude) {
        List<Office> offices = officeRepository.findAll();

        if (offices.isEmpty()) {
            return null;
        }

        Office nearestOffice = null;
        double minDistance = Double.MAX_VALUE;

        for (Office office : offices) {
            double distance = calculateDistance(latitude, longitude, office.getLatitude(), office.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestOffice = office;
            }
        }

        return nearestOffice;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }

    public void createOffice(String salePointName, String address, String status, String rko, String officeType, String salePointFormat, String suoAvailability, String hasRamp, Double latitude, Double longitude, String metroStation, Integer distance, Boolean kep, Boolean myBranch) {
        Office office = new Office();
        office.setSalePointName(salePointName);
        office.setDistance(distance);
        office.setHasRamp(hasRamp);
        office.setKep(kep);
        office.setLatitude(latitude);
        office.setLongitude(longitude);
        office.setMetroStation(metroStation);
        office.setMyBranch(myBranch);
        office.setOfficeType(officeType);
        office.setRko(rko);
        office.setSalePointFormat(salePointFormat);
        office.setStatus(status);
        office.setAddress(address);
        officeRepository.save(office);
    }
}