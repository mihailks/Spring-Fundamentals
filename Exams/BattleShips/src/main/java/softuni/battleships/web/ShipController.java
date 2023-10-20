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
import softuni.battleships.model.bindingModel.AddShipDTO;
import softuni.battleships.service.ShipService;
import softuni.battleships.util.CurrentUser;

@Controller
@RequestMapping("/ships")
public class ShipController {
    private final ShipService shipService;
    private final CurrentUser currentUser;

    public ShipController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    private AddShipDTO addShipDTO(){
        return new AddShipDTO();
    }
    @GetMapping("/add")
    public String addShip() {
        if (currentUser.isLogged()) {
            return "ship-add";
        }
        return "redirect:/users/login";
    }

    @PostMapping("/add")
    private String addShip(@Valid AddShipDTO addShipDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addShipDTO", addShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShipDTO", bindingResult);
            return "redirect:add";
        }

        shipService.addShip(addShipDTO);



        return "redirect:/home";
    }
}
