package com.resellerapp.web;

import com.resellerapp.model.bindingModel.AddOfferBindingModel;
import com.resellerapp.model.serviceModel.OfferServiceModel;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final CurrentUser currentUser;
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OfferController(CurrentUser currentUser, OfferService offerService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute
    public AddOfferBindingModel addOfferBindingModel(){
        return new AddOfferBindingModel();
    }
    @GetMapping("/add")
    public String addOffer() {
        if (currentUser.isLogged()) {
            return "offer-add";
        }
        return "redirect:/users/login";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferBindingModel addOfferBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("addOfferBindingModel", addOfferBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferBindingModel",
                            bindingResult);
            return "redirect:/offers/add";
        }

        offerService.addOffer(modelMapper.map(addOfferBindingModel, OfferServiceModel.class));


        return "redirect:/home";
    }


}
