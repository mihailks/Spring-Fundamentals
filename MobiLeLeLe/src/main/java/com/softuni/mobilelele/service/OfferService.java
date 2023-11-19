package com.softuni.mobilelele.service;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.model.dto.OfferDetailDTO;
import com.softuni.mobilelele.model.dto.OfferSummeryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface OfferService {
    UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails owner);

    Page<OfferSummeryDTO> getAllOffers(Pageable pageable);

    Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID, UserDetails viewer);

    void deleteOffer(UUID offerUUID);

    Boolean isOwner(UUID offerUUID, UserDetails owner);

}
