package com.dictionaryapp.controller;

import com.dictionaryapp.model.DTO.UserLoginDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserLoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (currentUser.isLogged()) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            return "redirect:/users/login";
        }

        CurrentUser findUserByUsernameAndPassword = userService.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        if (findUserByUsernameAndPassword == null){
            redirectAttributes.addFlashAttribute("isFound", false);
            return "redirect:login";
        }
        userService.loginUser(findUserByUsernameAndPassword.getId(), userLoginDTO.getUsername());

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logoutUser();
        return "redirect:/";
    }


}
