package com.dictionaryapp.service;

import com.dictionaryapp.model.DTO.AddWordDTO;
import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import com.dictionaryapp.model.view.WordViewModel;

import java.util.List;

public interface WordService {
    void addWord(AddWordDTO addWordDTO);

    List<WordViewModel> findAllWordsByLanguage(LanguageNameEnum name);

    void deleteWordByID(Long id);

    void deleteAll();
}
