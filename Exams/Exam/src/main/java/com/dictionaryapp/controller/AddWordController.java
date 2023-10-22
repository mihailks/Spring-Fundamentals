package com.dictionaryapp.controller;

import com.dictionaryapp.model.DTO.AddWordDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class AddWordController {
    private final WordService wordService;
    private final CurrentUser currentUser;

    public AddWordController(WordService wordService, CurrentUser currentUser) {
        this.wordService = wordService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addWord(Model model) {
        if (!currentUser.isLogged()) {
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("wordAddDTO")) {
            model.addAttribute("wordAddDTO", new AddWordDTO());
        }
        return "word-add";
    }

    @PostMapping("/add")
    public String addWordConfirm(@Valid AddWordDTO addWordDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordAddDTO", addWordDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.wordAddDTO", bindingResult);
            return "redirect:add";
        }

        wordService.addWord(addWordDTO);

        return "redirect:/home";
    }


}
