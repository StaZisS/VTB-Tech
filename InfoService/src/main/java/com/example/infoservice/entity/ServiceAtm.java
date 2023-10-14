package com.example.infoservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class ServiceAtm {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "wheelchair_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "wheelchair_activity"))
    })
    private ServiceAtmDetails wheelchair;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "blind_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "blind_activity"))
    })
    private ServiceAtmDetails blind;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "nfc_for_bank_cards_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "nfc_for_bank_cards_activity"))
    })
    private ServiceAtmDetails nfcForBankCards;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "qr_reader_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "qr_reader_activity"))
    })
    private ServiceAtmDetails qrRead;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_usd_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_usd_activity"))
    })
    private ServiceAtmDetails supportsUsd;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_charge_rub_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_charge_rub_activity"))
    })
    private ServiceAtmDetails supportsChargeRub;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_eur_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_eur_activity"))
    })
    private ServiceAtmDetails supportsEur;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_rub_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_rub_activity"))
    })
    private ServiceAtmDetails supportsRub;
}
