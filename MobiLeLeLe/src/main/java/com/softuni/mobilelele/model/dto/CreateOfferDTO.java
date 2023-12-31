package com.softuni.mobilelele.model.dto;

import com.softuni.mobilelele.model.entity.enums.EngineEnum;
import com.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import com.softuni.mobilelele.model.validation.YearNotInTheFuture;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateOfferDTO(@Size(min = 5, max = 512)
                             @NotEmpty String description,
                             @NotNull Long modelId,
                             @NotNull EngineEnum engine,
                             @NotNull TransmissionEnum transmission,
                             @NotEmpty String imageUrl,
                             @NotNull @Positive Integer mileage,
                             @NotNull @Positive BigDecimal price,
                             @YearNotInTheFuture
                             @NotNull(message = "year can`t be empty")
                             @Positive
                             @Min(1930)
                             Integer year) {



        public static CreateOfferDTO createEmpty(){
                return new CreateOfferDTO(
                        null, null, null, null, null, null, null, null);
        }


}
