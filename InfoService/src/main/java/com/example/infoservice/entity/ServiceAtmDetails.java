package com.example.infoservice.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Embeddable
public class ServiceAtmDetails {
    @Enumerated(EnumType.ORDINAL)
    private ServiceCapability serviceCapability;

    @Enumerated(EnumType.ORDINAL)
    private ServiceActivity serviceActivity;
}