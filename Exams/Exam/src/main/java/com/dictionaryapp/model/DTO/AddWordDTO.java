package com.dictionaryapp.model.DTO;

import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddWordDTO {
    @NotNull
    @Size(min=2, max= 40, message = "Term must be between 2 and 40 characters!")
    private String term;
    @NotNull
    @Size(min=2, max= 80, message = "Translation must be between 2 and 80 characters!")
    private String translation;
    @NotNull
    @Size(min=2, max= 200, message = "Example must be between 2 and 200 characters!")
    private String example;
    @NotNull(message = "You must select a date!")
    @PastOrPresent(message = "The input date must be in the past or present!")
    private LocalDate date;
    @NotNull(message = "You must select a language!")
    private LanguageNameEnum language;

    public AddWordDTO() {
    }

    public String getTerm() {
        return term;
    }

    public AddWordDTO setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public AddWordDTO setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public AddWordDTO setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public AddWordDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LanguageNameEnum getLanguage() {
        return language;
    }

    public AddWordDTO setLanguage(LanguageNameEnum language) {
        this.language = language;
        return this;
    }
}
