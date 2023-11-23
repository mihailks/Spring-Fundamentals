package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.model.dto.OfferDetailDTO;
import com.softuni.mobilelele.model.dto.OfferSummeryDTO;
import com.softuni.mobilelele.model.entity.ModelEntity;
import com.softuni.mobilelele.model.entity.OfferEntity;
import com.softuni.mobilelele.repository.ModelRepository;
import com.softuni.mobilelele.repository.OfferRepository;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.OfferService;
import com.softuni.mobilelele.service.RecaptchaService;
import com.softuni.mobilelele.service.aop.WornIfTimeExecutionExceeds;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.lang.Thread.sleep;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails owner) {
        OfferEntity newOffer = map(createOfferDTO);
        ModelEntity modelEntity = modelRepository.findById(createOfferDTO.modelId())
                .orElseThrow(() -> new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found!"));
        newOffer.setModel(modelEntity);
        newOffer.setSeller(userRepository.findByEmail(owner.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User with email " + owner.getUsername() + " not found!")));

        offerRepository.save(newOffer);
        return newOffer.getUuid();
    }

    @WornIfTimeExecutionExceeds(timeInMillis = 1000L)
    @Override
    public Page<OfferSummeryDTO> getAllOffers(Pageable pageable) {
//        try { // for testing purposes
//            sleep(1000L);
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted!");
//        }
        return offerRepository
                .findAll(pageable)
                .map(OfferServiceImpl::mapAsSummary);
    }

    @WornIfTimeExecutionExceeds(timeInMillis = 500L)
    @Override
    public Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID, UserDetails viewer) {
        return offerRepository
                .findByUuid(offerUUID)
                .map(offerEntity -> this.mapAsDetails(offerEntity, viewer));
    }

    @Override
    @Transactional
    public void deleteOffer(UUID offerUUID) {
        offerRepository.deleteByUuid(offerUUID);
    }

    @Override
    public Boolean isOwner(UUID offerUUID, UserDetails owner) {
        OfferEntity offerEntity = offerRepository.findByUuid(offerUUID).orElse(null);
        if (offerEntity == null) {
            return false;
        }
        return isOwner(offerEntity, owner);
    }

    private Boolean isOwner(OfferEntity offerEntity, UserDetails viewer) {
        if (viewer == null) {
            return false;
        }
        boolean isAdmin = viewer.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return true;
        }
        return offerEntity.getSeller().getEmail().equals(viewer.getUsername());
    }

    private static OfferSummeryDTO mapAsSummary(OfferEntity offerEntity) {
        return new OfferSummeryDTO(
                offerEntity.getUuid(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl());
    }

    private OfferEntity map(CreateOfferDTO createOfferDTO) {
        return new OfferEntity()
                .setUuid(UUID.randomUUID())
                .setDescription(createOfferDTO.description())
                .setEngine(createOfferDTO.engine())
                .setTransmission(createOfferDTO.transmission())
                .setImageUrl(createOfferDTO.imageUrl())
                .setMileage(createOfferDTO.mileage())
                .setPrice(createOfferDTO.price())
                .setYear(createOfferDTO.year());
    }

    private OfferDetailDTO mapAsDetails(OfferEntity offerEntity, UserDetails viewer) {
        return new OfferDetailDTO(
                offerEntity.getUuid(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl(),
                offerEntity.getSeller().getFirstName() + " " + offerEntity.getSeller().getLastName(),
                isOwner(offerEntity, viewer));
    }

}
