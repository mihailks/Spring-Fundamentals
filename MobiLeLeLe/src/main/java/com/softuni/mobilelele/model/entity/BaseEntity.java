package com.softuni.mobilelele.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", type = org.hibernate.id.uuid.UuidGenerator.class)
//    private String UUID;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getUUID() {
//        return UUID;
//    }
//
//    public void setUUID(String UUID) {
//        this.UUID = UUID;
//    }
}
