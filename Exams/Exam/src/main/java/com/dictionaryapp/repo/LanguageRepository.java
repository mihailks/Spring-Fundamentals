package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

    Optional<LanguageEntity> findByName(LanguageNameEnum language);
}
