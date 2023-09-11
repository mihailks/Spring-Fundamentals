package softuni.coffeeshop.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.coffeeshop.model.binding.UserRegisterBindingModel;
import softuni.coffeeshop.model.service.UserServiceModel;
import softuni.coffeeshop.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelmapper;
    private final UserService userService;

    public UserController(ModelMapper modelmapper, UserService userService) {
        this.modelmapper = modelmapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {


        return ("register");
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()
                || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel",
                    userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);


            return "redirect:register";
        }


        //save user in DB

        userService.registerUser(modelmapper
                .map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
}
