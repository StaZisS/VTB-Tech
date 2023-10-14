package com.example.mobiletech;

import com.example.mobiletech.entity.Office;
import com.example.mobiletech.service.OfficeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileTechApplication implements CommandLineRunner {

    private final OfficeRepository officeRepository;
    private final OfficeService officeService;

    public MobileTechApplication(OfficeRepository officeRepository, OfficeService officeService) {
        this.officeRepository = officeRepository;
        this.officeService = officeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MobileTechApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        officeService.createOffice("Office 5", "123 Main St",
                "Open", "RKO", "Office Type", "Sale Point Format",
                "Suo Availability", "Has Ramp", 864.2, 873.1, "Metro Station",
                5000, true, true);
        officeService.createOffice("Office 6", "123 Main St",
                "Open", "RKO", "Office Type", "Sale Point Format",
                "Suo Availability", "Has Ramp", 633.2, 755.2, "Metro Station",
                3000, true, true);
        for (Office office : officeRepository.findAll()) {
            System.out.println(office);
        }
    }

}
