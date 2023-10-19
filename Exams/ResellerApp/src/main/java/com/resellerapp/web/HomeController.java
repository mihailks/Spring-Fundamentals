package com.resellerapp.web;

import com.resellerapp.model.viewModel.OfferAndUserNameViewModel;
import com.resellerapp.model.viewModel.UserOffersViewModel;
import com.resellerapp.service.ConditionService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final CurrentUser currentUser;
    private final OfferService offerService;
    private final ConditionService conditionService;

    public HomeController(UserService userService, CurrentUser currentUser, OfferService offerService, ConditionService conditionService) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.offerService = offerService;
        this.conditionService = conditionService;
    }

    @GetMapping("/home")
    private String index(Model model) {
        if (!currentUser.isLogged()) {
            return "redirect:/users/login";
        }

        List<UserOffersViewModel> myOffers = offerService.findAllOffersByUserIdNoBuyer(currentUser.getId());
        List<UserOffersViewModel> myBoughtItems = offerService.findAllOffersByUserIdBoughtByMe(currentUser.getId());
        List<OfferAndUserNameViewModel> otherOffers = offerService.findAllOtherOffers(currentUser.getId());

        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("offers", myOffers);
        model.addAttribute("bought", myBoughtItems);
        model.addAttribute("otherOffers", otherOffers);

        return "home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        offerService.deleteOfferByID(id);
        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    private String buy(@PathVariable Long id){
        offerService.buyOffer(id, currentUser.getId());
        return "redirect:/home";
    }
}
