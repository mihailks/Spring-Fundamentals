package com.resellerapp.service.impl;

import com.resellerapp.model.entity.ConditionEntity;
import com.resellerapp.model.entity.OfferEntity;
import com.resellerapp.model.entity.UserEntity;
import com.resellerapp.model.serviceModel.OfferServiceModel;
import com.resellerapp.model.viewModel.OfferAndUserNameViewModel;
import com.resellerapp.model.viewModel.UserOffersViewModel;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.service.ConditionService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ConditionService conditionService;

    public OfferServiceImpl(OfferRepository offerRepository, CurrentUser currentUser, ModelMapper modelMapper, UserService userService, ConditionRepository conditionRepository, ConditionService conditionService) {
        this.offerRepository = offerRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.userService = userService;

        this.conditionService = conditionService;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) {
        OfferEntity offerEntity = modelMapper.map(offerServiceModel, OfferEntity.class);
        UserEntity createdBy = userService.findById(currentUser.getId());
        ConditionEntity condition = conditionService.findCondition(offerServiceModel.getCondition());

        offerEntity.setCreatedBy(createdBy)
                .setCondition(condition);


        offerRepository.save(offerEntity);
    }

    @Override
    public List<UserOffersViewModel> findAllOffersByUserIdNoBuyer(Long id) {

        return offerRepository.findByCreatedBy_IdAndBoughtByIsNull(id)
                .stream()
                .map(offer -> {
                    UserOffersViewModel userOffersViewModel = modelMapper.map(offer, UserOffersViewModel.class);
                    userOffersViewModel.setCondition(offer.getCondition().getName().name());
                    return userOffersViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<UserOffersViewModel> findAllOffersByUserIdBoughtByMe(Long id) {

        return offerRepository.findByBoughtBy_Id(id)
                .stream()
                .map(offer -> {
                    UserOffersViewModel userOffersViewModel = modelMapper.map(offer, UserOffersViewModel.class);
                    userOffersViewModel.setCondition(offer.getCondition().getName().name());
                    return userOffersViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<OfferAndUserNameViewModel> findAllOtherOffers(Long id) {
        return offerRepository.findAllByBoughtByIsNullAndCreatedBy_IdIsNot(id)
                .stream()
                .map(offer -> {
                    OfferAndUserNameViewModel offerAndUserNameViewModel = modelMapper.map(offer, OfferAndUserNameViewModel.class);
                    offerAndUserNameViewModel.setCondition(offer.getCondition().getName().name());
                    offerAndUserNameViewModel.setUsername(offer.getCreatedBy().getUsername());
                    return offerAndUserNameViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void deleteOfferByID(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public void buyOffer(Long offerId, Long userId) {
        offerRepository.findById(offerId)
                .ifPresent(offer -> {
                    offer.setBoughtBy(userService.findById(userId));
                    offerRepository.save(offer);
                });
    }
}
