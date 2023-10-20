package softuni.battleships.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.battleships.model.bindingModel.UserRegisterDTO;
import softuni.battleships.service.UserService;

@Controller
@RequestMapping("/users")
public class UserRegisterController {
    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterDTO")) {
            model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
        redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
        return "redirect:register";
    }

        userService.registerUser(userRegisterDTO);


        return "redirect:/home";
    }


}
