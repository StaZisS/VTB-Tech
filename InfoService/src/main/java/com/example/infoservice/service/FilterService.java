package com.example.infoservice.service;

import com.example.infoservice.dto.FilterAtmsDto;
import com.example.infoservice.dto.FilterDepartmentDto;
import com.example.infoservice.entity.*;
import com.example.infoservice.repository.AtmRepo;
import com.example.infoservice.repository.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FilterService {
    private final DepartmentRepo departmentRepo;
    private final AtmRepo atmRepo;

    public List<DepartmentEntity> filterDepartment(FilterDepartmentDto filterDepartmentDto){
        if(filterDepartmentDto == null){
            return departmentRepo.findAll();
        }
        return departmentRepo.findAll().stream().filter(departmentEntity -> {
            if(filterDepartmentDto.getHasRamp() != null && departmentEntity.getHasRamp() != null){
                return departmentEntity.getHasRamp().equals(filterDepartmentDto.getHasRamp());
            }
            return true;
        }).filter(departmentEntity -> {
            if(filterDepartmentDto.getServices() != null){
                if (departmentEntity.getServices().isEmpty()){
                    return false;
                }
                return departmentEntity.getServices().stream()
                        .map(ServiceDepartment::getServiceDepartmentEnum)
                        .collect(Collectors.toSet())
                        .containsAll(filterDepartmentDto.getServices());
            }
            return true;
        }).collect(Collectors.toList());
    }

    public List<AtmEntity> filterAtm(FilterAtmsDto filterAtmsDto){
        if(filterAtmsDto == null){
            return atmRepo.findAll();
        }
        var avlb = ServiceActivity.AVAILABLE;
        return atmRepo.findAll().stream().filter(atmEntity -> {
            if(filterAtmsDto.getAtmsOptions() != null){
                for (var option : filterAtmsDto.getAtmsOptions()){
                    var services = atmEntity.getServices();
                    if(option == AtmsOptionEnum.WHEELCHAIR && services.getWheelchair().getServiceActivity() != avlb){
                        return false;
                    }
                    if(option == AtmsOptionEnum.BLIND && services.getBlind().getServiceActivity() != avlb){
                        return false;
                    }
                    if(option == AtmsOptionEnum.NFC_FOR_BANK_CARDS && services.getNfcForBankCards().getServiceActivity() != avlb){
                        return false;
                    }
                    if(option == AtmsOptionEnum.QR_READ && services.getQrRead().getServiceActivity() != avlb){
                        return false;
                    }
                    if(option == AtmsOptionEnum.SUPPORT_USD && services.getSupportsUsd().getServiceActivity() != avlb){
                        return false;
                    }
                    if(option == AtmsOptionEnum.SUPPORT_CHARGE_RUB && services.getSupportsChargeRub().getServiceActivity() != avlb){
                        return false;
                    }
                    if(option == AtmsOptionEnum.SUPPORT_EUR && services.getSupportsEur().getServiceActivity() != avlb){
                        return false;
                    }
                    if(option == AtmsOptionEnum.SUPPORT_RUB && services.getSupportsRub().getServiceActivity() != avlb){
                        return false;
                    }
                    return true;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}
