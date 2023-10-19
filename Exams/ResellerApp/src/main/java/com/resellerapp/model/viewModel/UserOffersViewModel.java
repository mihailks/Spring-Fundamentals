package com.resellerapp.model.viewModel;

import java.math.BigDecimal;

public class UserOffersViewModel {
    private Long id;
    private String condition;
    private BigDecimal price;
    private String description;

    public UserOffersViewModel() {
    }

    public String getCondition() {
        return condition;
    }

    public UserOffersViewModel setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserOffersViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserOffersViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserOffersViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
