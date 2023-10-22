package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.enums.LanguageNameEnum;

public interface LanguageService {
    void initLanguages();

    LanguageEntity findLanguageByName(LanguageNameEnum language);
}
