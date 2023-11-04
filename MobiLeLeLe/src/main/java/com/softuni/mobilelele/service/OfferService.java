package com.softuni.mobilelele.service;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.model.dto.OfferDetailDTO;
import com.softuni.mobilelele.model.dto.OfferSummeryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OfferService {
    UUID createOffer(CreateOfferDTO createOfferDTO);

    Page<OfferSummeryDTO> getAllOffers(Pageable pageable);

    Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID);

    void deleteOffer(UUID offerUUID);

}
