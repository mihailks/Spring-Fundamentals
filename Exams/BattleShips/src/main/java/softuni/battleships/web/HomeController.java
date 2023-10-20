package softuni.battleships.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.battleships.model.bindingModel.BattleDTO;
import softuni.battleships.model.viewModel.ShipViewModel;
import softuni.battleships.service.ShipService;
import softuni.battleships.util.CurrentUser;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
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


        List<ShipViewModel> attackerShips = shipService.findAllShipsByUserId(currentUser.getId());
        List<ShipViewModel> defenderShips = shipService.findAllShipsByUserIdIsNot(currentUser.getId());
        List<ShipViewModel> allShips = shipService.findAllShips();

        model.addAttribute("attackerShips", attackerShips)
                .addAttribute("defenderShips", defenderShips)
                .addAttribute("allShips", allShips);
        return "home";
    }

    @PostMapping("/fire")
    public String homeConfirm(BattleDTO battleDTO) {

        this.shipService.battle(battleDTO);

        return "redirect:/home";
    }

    @ModelAttribute
    public BattleDTO battleDTO() {
        return new BattleDTO();
    }


}
