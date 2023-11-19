package com.softuni.mobilelele.web;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.model.dto.OfferDetailDTO;
import com.softuni.mobilelele.model.entity.enums.EngineEnum;
import com.softuni.mobilelele.service.BrandService;
import com.softuni.mobilelele.service.OfferService;
import com.softuni.mobilelele.service.exeption.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
    }


    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("createOfferDTO")) {
            model.addAttribute("createOfferDTO", CreateOfferDTO.createEmpty());
        }

        model.addAttribute("brands", brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String add(@Valid CreateOfferDTO createOfferDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      @AuthenticationPrincipal UserDetails owner) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
            return "redirect:add";
        }

        UUID newOfferUUID = offerService.createOffer(createOfferDTO, owner);

//        return "redirect:/offer/" + newOfferUUID + "/details";
        return "redirect:/offer/" + newOfferUUID;
    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model,
                          @AuthenticationPrincipal UserDetails viewer) {

        OfferDetailDTO offerDetailDTO = offerService
                .getOfferDetail(uuid, viewer)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + uuid + " not found!"));

        model.addAttribute("offer", offerDetailDTO);

        return "details";
    }

@PreAuthorize("@offerServiceImpl.isOwner(#uuid, #principal)")
    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid, @AuthenticationPrincipal UserDetails principal){

        offerService.deleteOffer(uuid);

        return "redirect:/offers/all";
    }

}
