package softuni.battleships.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.battleships.model.bindingModel.UserLoginDTO;
import softuni.battleships.repository.UserRepository;
import softuni.battleships.service.UserService;
import softuni.battleships.util.CurrentUser;

@Controller
@RequestMapping("/users")
public class UserLoginController {
    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login(Model model) {
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
            return "redirect:login";
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
