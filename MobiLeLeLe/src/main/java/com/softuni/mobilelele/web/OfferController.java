package com.softuni.mobilelele.web;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.model.entity.enums.EngineEnum;
import com.softuni.mobilelele.service.BrandService;
import com.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {
    private OfferService offerService;
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
        model.addAttribute("brands", brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String add(CreateOfferDTO createOfferDTO) {

        offerService.createOffer(createOfferDTO);

        return "index";
    }

    @GetMapping("/{uuid}/details")
    public String details(@PathVariable("uuid") UUID uuid) {
        return "details";
    }


}
