package com.softuni.mobilelele.model.dto;

import com.softuni.mobilelele.model.entity.ModelEntity;
import com.softuni.mobilelele.model.entity.enums.EngineEnum;
import com.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateOfferDTO(
        @Size(min = 5, max = 512)
        @NotEmpty
        String description,

        @NotNull
        @Positive
        ModelEntity model,

        @NotNull
        EngineEnum engine,

        @NotNull
        TransmissionEnum transmission,

        @NotEmpty
        String imageUrl,

        @NotNull
        @Positive
        Long mileage,

        @NotNull
        @Positive
        BigDecimal price,

        @Positive
        @Min(1930)
        int year
) {
}
