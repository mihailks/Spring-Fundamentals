package com.softuni.mobilelele.service;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;

import java.util.UUID;

public interface OfferService {
    UUID createOffer(CreateOfferDTO createOfferDTO);
}
