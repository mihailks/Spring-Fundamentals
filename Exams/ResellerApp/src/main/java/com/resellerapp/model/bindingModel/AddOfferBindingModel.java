package com.resellerapp.model.bindingModel;

import com.resellerapp.model.entity.enums.ConditionNameEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddOfferBindingModel {
    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;
    @NotNull
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;
    @NotNull(message = "You must select condition!")
    private ConditionNameEnum condition;

    public AddOfferBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public AddOfferBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public AddOfferBindingModel setCondition(ConditionNameEnum condition) {
        this.condition = condition;
        return this;
    }
}
