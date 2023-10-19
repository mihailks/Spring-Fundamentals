package com.resellerapp.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @NotNull
    @ManyToOne
    private ConditionEntity condition;
    @ManyToOne
    @NotNull
    private UserEntity createdBy;
    @ManyToOne
    private UserEntity boughtBy;

    public OfferEntity() {
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionEntity getCondition() {
        return condition;
    }

    public OfferEntity setCondition(ConditionEntity condition) {
        this.condition = condition;
        return this;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public OfferEntity setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public UserEntity getBoughtBy() {
        return boughtBy;
    }

    public OfferEntity setBoughtBy(UserEntity boughtBy) {
        this.boughtBy = boughtBy;
        return this;
    }
}
