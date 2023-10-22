package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {

    List<WordEntity> findAllByLanguage_Name(LanguageNameEnum language);
}
