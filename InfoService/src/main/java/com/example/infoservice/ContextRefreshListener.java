package com.example.infoservice;

import com.example.infoservice.entity.AtmEntity;
import com.example.infoservice.entity.DepartmentEntity;
import com.example.infoservice.entity.ServiceDepartment;
import com.example.infoservice.entity.ServiceDepartmentEnum;
import com.example.infoservice.repository.AtmRepo;
import com.example.infoservice.repository.DepartmentRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@Component
@Order(LOWEST_PRECEDENCE)
@AllArgsConstructor
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    private final DepartmentRepo departmentRepo;
    private final AtmRepo atmRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        InputStream departmentFile = getClass().getClassLoader().getResourceAsStream("json/offices.json");
        try {
            var departments = new com.fasterxml.jackson.databind.ObjectMapper().readValue(departmentFile, DepartmentEntity[].class);
            for(var department : departments){
                for (var openHours : department.getOpenHours()) {
                    openHours.setDepartmentEntity(department);
                }

                for (var openHoursIndividual : department.getOpenHoursIndividual()) {
                    openHoursIndividual.setDepartmentEntity(department);
                }

                var services = generateList();
                for(var service : services){
                    service.setDepartmentEntity(department);
                }
                department.setServices(services);

            }
            departmentRepo.saveAll(Arrays.asList(departments));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var officeFile = getClass().getClassLoader().getResourceAsStream("json/atms.json");
        try {
            var offices = new com.fasterxml.jackson.databind.ObjectMapper().readValue(officeFile, AtmEntity[].class);
            atmRepo.saveAll(Arrays.asList(offices));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private List<ServiceDepartment> generateList(){
        var res = new ArrayList<ServiceDepartment>();
        var countService = ServiceDepartmentEnum.values().length;
        var random = new Random();
        for (int i = 0; i < random.nextInt(countService); i++) {
            var service = new ServiceDepartment();
            service.setServiceDepartmentEnum(ServiceDepartmentEnum.values()[random.nextInt(countService)]);
            res.add(service);
        }
        return res;
    }


}
