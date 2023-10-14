package com.example.mobiletech.service;

import com.example.mobiletech.OfficeRepository;
import com.example.mobiletech.entity.Office;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> findNearestOffices(Double latitude, Double longitude, int numberOfOffices) {
        List<Office> offices = officeRepository.findAll();

        if (offices.isEmpty()) {
            return new ArrayList<>();
        }

        Queue<Office> nearestOfficesQueue = new PriorityQueue<>(numberOfOffices, Comparator.comparingDouble(office ->
                calculateUserDistance(latitude, longitude, office.getLatitude(), office.getLongitude())));

        for (Office office : offices) {
            double distance = calculateUserDistance(latitude, longitude, office.getLatitude(), office.getLongitude());
            if (nearestOfficesQueue.size() < numberOfOffices || distance < Objects.requireNonNull(nearestOfficesQueue.peek()).getDistance()) {
                office.setDistance(distance);
                nearestOfficesQueue.offer(office);
            }
            if (nearestOfficesQueue.size() > numberOfOffices) {
                nearestOfficesQueue.poll();
            }
        }
        List<Office> nearestOffices = new ArrayList<>(nearestOfficesQueue);
        nearestOffices.sort(Comparator.comparing(Office::getDistance));
        return nearestOffices;
    }

    private double calculateUserDistance(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }


    public void createOffice(String salePointName, String address,
                             String status, String rko, String officeType,
                             String salePointFormat, String suoAvailability,
                             String hasRamp, Double latitude, Double longitude,
                             String metroStation, Double distance,
                             Boolean kep, Boolean myBranch) {
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