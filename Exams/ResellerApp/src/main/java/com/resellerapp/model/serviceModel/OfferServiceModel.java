package com.resellerapp.model.serviceModel;

import com.resellerapp.model.entity.ConditionEntity;
import com.resellerapp.model.entity.UserEntity;

import java.math.BigDecimal;

public class OfferServiceModel {

    private String id;
    private String description;
    private BigDecimal price;
    private ConditionEntity condition;
    private UserEntity createdBy;
    private UserEntity boughtBy;

    public OfferServiceModel() {
    }

    public String getId() {
        return id;
    }

    public OfferServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionEntity getCondition() {
        return condition;
    }

    public OfferServiceModel setCondition(ConditionEntity condition) {
        this.condition = condition;
        return this;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public OfferServiceModel setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public UserEntity getBoughtBy() {
        return boughtBy;
    }

    public OfferServiceModel setBoughtBy(UserEntity boughtBy) {
        this.boughtBy = boughtBy;
        return this;
    }
}
