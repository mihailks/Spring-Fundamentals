package com.dictionaryapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "words")
public class WordEntity extends BaseEntity {
    @Column(nullable = false)
    private String term;
    @Column(nullable = false)
    private String translation;
    @Column(columnDefinition = "TEXT")
    private String example;
    @Column(name = "input_date", nullable = false)
    private LocalDate date;
    @ManyToOne
    private LanguageEntity language;
    @ManyToOne
    private UserEntity addedBy;

    public WordEntity() {
    }

    public String getTerm() {
        return term;
    }

    public WordEntity setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordEntity setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordEntity setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public WordEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public WordEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public UserEntity getAddedBy() {
        return addedBy;
    }

    public WordEntity setAddedBy(UserEntity addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
