package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.DTO.AddWordDTO;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import com.dictionaryapp.model.view.WordViewModel;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final LanguageService languageService;
    private final UserService userService;

    public WordServiceImpl(WordRepository wordRepository, ModelMapper modelMapper, CurrentUser currentUser, LanguageService languageService, UserService userService) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.languageService = languageService;
        this.userService = userService;
    }

    @Override
    public void addWord(AddWordDTO addWordDTO) {
        WordEntity word = modelMapper.map(addWordDTO, WordEntity.class);
        LanguageEntity language = languageService.findLanguageByName(addWordDTO.getLanguage());
        UserEntity user = userService.findById(currentUser.getId());

        word.setLanguage(language).setAddedBy(user);

        wordRepository.save(word);
    }

    @Override
    public List<WordViewModel> findAllWordsByLanguage(LanguageNameEnum name) {
        return getWordViewModel(name);
    }

    @Override
    public void deleteWordByID(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        wordRepository.deleteAll();
    }

    private List<WordViewModel> getWordViewModel(LanguageNameEnum name) {
        return wordRepository.findAllByLanguage_Name(name)
                .stream()
                .map(word -> {
                    WordViewModel wordView = modelMapper.map(word, WordViewModel.class);
                    wordView.setUsername(word.getAddedBy().getUsername());
                    return wordView;
                })
                .collect(Collectors.toList());
    }
}
