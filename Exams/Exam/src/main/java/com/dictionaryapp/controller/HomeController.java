package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.enums.LanguageNameEnum;
import com.dictionaryapp.model.view.WordViewModel;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    private final WordService wordService;
    private final CurrentUser currentUser;

    public HomeController(WordService wordService, CurrentUser currentUser) {
        this.wordService = wordService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLogged()) {
            return "redirect:/users/login";
        }

        List<WordViewModel> germanWords = wordService.findAllWordsByLanguage(LanguageNameEnum.GERMAN);
        List<WordViewModel> spanishWords = wordService.findAllWordsByLanguage(LanguageNameEnum.SPANISH);
        List<WordViewModel> frenchWords = wordService.findAllWordsByLanguage(LanguageNameEnum.FRENCH);
        List<WordViewModel> italianWords = wordService.findAllWordsByLanguage(LanguageNameEnum.ITALIAN);
        int totalWords = germanWords.size() + spanishWords.size() + frenchWords.size() + italianWords.size();

        model.addAttribute("germanWords", germanWords);
        model.addAttribute("spanishWords", spanishWords);
        model.addAttribute("frenchWords", frenchWords);
        model.addAttribute("italianWords", italianWords);
        model.addAttribute("totalWords", totalWords);

        return "home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!currentUser.isLogged()) {
            return "redirect:/users/login";
        }

        wordService.deleteWordByID(id);
        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {
        if (!currentUser.isLogged()) {
            return "redirect:/users/login";
        }

        wordService.deleteAll();
        return "redirect:/home";
    }

}
