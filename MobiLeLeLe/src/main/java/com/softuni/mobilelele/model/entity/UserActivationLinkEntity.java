package com.softuni.mobilelele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "user_activation_codes")
public class UserActivationLinkEntity extends BaseEntity {
    private String activationCode;
    private Instant created;
    @ManyToOne
    private UserEntity userEntity;

    public UserActivationLinkEntity() {
    }

    public String getActivationCode() {
        return activationCode;
    }

    public UserActivationLinkEntity setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public UserActivationLinkEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public UserActivationLinkEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
