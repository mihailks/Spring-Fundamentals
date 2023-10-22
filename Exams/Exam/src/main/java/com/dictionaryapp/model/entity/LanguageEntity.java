package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private LanguageNameEnum name;
    @Column(nullable = false)
    private String description;

    public LanguageEntity() {
    }

    public LanguageNameEnum getName() {
        return name;
    }

    public LanguageEntity setName(LanguageNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LanguageEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
