package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.model.dto.OfferDetailDTO;
import com.softuni.mobilelele.model.dto.OfferSummeryDTO;
import com.softuni.mobilelele.model.entity.ModelEntity;
import com.softuni.mobilelele.model.entity.OfferEntity;
import com.softuni.mobilelele.repository.ModelRepository;
import com.softuni.mobilelele.repository.OfferRepository;
import com.softuni.mobilelele.service.OfferService;
import com.softuni.mobilelele.service.exeption.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {
        OfferEntity newOffer = map(createOfferDTO);
        ModelEntity modelEntity = modelRepository.findById(createOfferDTO.modelId())
                .orElseThrow(()-> new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found!"));
        newOffer.setModel(modelEntity);

        offerRepository.save(newOffer);
        return newOffer.getUuid();
    }

    @Override
    public Page<OfferSummeryDTO> getAllOffers(Pageable pageable) {
        return offerRepository
                .findAll(pageable)
                .map(OfferServiceImpl::mapAsSummary);
    }

    @Override
    public Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID) {
        return offerRepository
                .findByUuid(offerUUID)
                .map(OfferServiceImpl::mapAsDetails);
    }

    @Override
    @Transactional
    public void deleteOffer(UUID offerUUID) {
        offerRepository.deleteByUuid(offerUUID);
    }

    private static OfferDetailDTO mapAsDetails(OfferEntity offerEntity){
        return new OfferDetailDTO(
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

    private static OfferSummeryDTO mapAsSummary(OfferEntity offerEntity){
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

    private OfferEntity map(CreateOfferDTO createOfferDTO){
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
}
