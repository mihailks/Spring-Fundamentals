package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void initLanguages() {
        if (this.languageRepository.count() != 0) {
            return;
        }

        Arrays.stream(LanguageNameEnum.values())
                .forEach(l -> {

                    LanguageEntity language = new LanguageEntity();
                    language.setName(l);

                    switch (l.name()) {
                        case "GERMAN":
                            language.setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                            break;
                        case "SPANISH":
                            language.setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                            break;
                        case "FRENCH":
                            language.setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                            break;
                        case "ITALIAN":
                            language.setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                            break;
                    }

                    this.languageRepository.save(language);
                });
    }

    @Override
    public LanguageEntity findLanguageByName(LanguageNameEnum language) {
        return this.languageRepository.findByName(language).orElse(null);
    }
}
