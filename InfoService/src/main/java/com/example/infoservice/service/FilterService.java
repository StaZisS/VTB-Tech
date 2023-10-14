package com.example.infoservice.service;

import com.example.infoservice.dto.FilterDto;
import com.example.infoservice.entity.DepartmentEntity;
import com.example.infoservice.entity.ServiceDepartment;
import com.example.infoservice.entity.ServiceDepartmentEnum;
import com.example.infoservice.repository.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FilterService {
    private final DepartmentRepo departmentRepo;

    public List<DepartmentEntity> filterDepartment(FilterDto filterDto){
        if(filterDto == null){
            return departmentRepo.findAll();
        }
        return departmentRepo.findAll().stream().filter(departmentEntity -> {
            if(filterDto.getHasRamp() != null && departmentEntity.getHasRamp() != null){
                return departmentEntity.getHasRamp().equals(filterDto.getHasRamp());
            }
            return true;
        }).filter(departmentEntity -> {
            if(filterDto.getServices() != null){
                if (departmentEntity.getServices().isEmpty()){
                    return false;
                }
                return departmentEntity.getServices().stream()
                        .map(ServiceDepartment::getServiceDepartmentEnum)
                        .collect(Collectors.toSet())
                        .containsAll(filterDto.getServices());
            }
            return true;
        }).collect(Collectors.toList());
    }
}
