package com.dictionaryapp.model.view;

import java.time.LocalDate;

public class WordViewModel {
    private Long id;
    private String term;
    private String translation;
    private String example;
    private LocalDate date;
    private String username;

    public WordViewModel() {
    }

    public String getTranslation() {
        return translation;
    }

    public WordViewModel setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordViewModel setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public WordViewModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public WordViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public WordViewModel setTerm(String term) {
        this.term = term;
        return this;
    }

    public Long getId() {
        return id;
    }

    public WordViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
