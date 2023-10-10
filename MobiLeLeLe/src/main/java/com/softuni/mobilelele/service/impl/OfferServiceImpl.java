package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.repository.OfferRepository;
import com.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {

        throw new UnsupportedOperationException();
    }
}
