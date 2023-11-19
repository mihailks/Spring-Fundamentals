package com.softuni.mobilelele.model.dto;

import com.softuni.mobilelele.model.entity.enums.EngineEnum;
import com.softuni.mobilelele.model.entity.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record OfferDetailDTO (UUID uuid, String brand, String model, int year, int mileage, BigDecimal price,
                             EngineEnum engine, TransmissionEnum transmission, String imageUrl, String sellerFullName,
                              Boolean viewerIsOwner) {

    public String summary() {
        return brand + " " + model + " " + (year);
    }

}
