package com.resellerapp.model.viewModel;

import java.math.BigDecimal;

public class OfferAndUserNameViewModel {
    private Long id;
    private String condition;
    private BigDecimal price;
    private String description;
    private String username;

    public OfferAndUserNameViewModel() {
    }

    public String getCondition() {
        return condition;
    }

    public OfferAndUserNameViewModel setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferAndUserNameViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferAndUserNameViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OfferAndUserNameViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferAndUserNameViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
