package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.CreateOfferDTO;
import com.softuni.mobilelele.model.entity.ModelEntity;
import com.softuni.mobilelele.model.entity.OfferEntity;
import com.softuni.mobilelele.repository.ModelRepository;
import com.softuni.mobilelele.repository.OfferRepository;
import com.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Service;

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
        ModelEntity modelEntity = modelRepository.findById(createOfferDTO.modelId()).orElseThrow(()->{
            throw new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found!");
        });
        newOffer.setModel(modelEntity);

        offerRepository.save(newOffer);
        return newOffer.getUuid();
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
