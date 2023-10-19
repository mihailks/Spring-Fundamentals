package com.resellerapp.service;

import com.resellerapp.model.serviceModel.OfferServiceModel;
import com.resellerapp.model.viewModel.OfferAndUserNameViewModel;
import com.resellerapp.model.viewModel.UserOffersViewModel;

import java.util.List;

public interface OfferService {
    void addOffer(OfferServiceModel offerServiceModel);

    List<UserOffersViewModel> findAllOffersByUserIdNoBuyer(Long id);

    List<UserOffersViewModel> findAllOffersByUserIdBoughtByMe(Long id);

    List<OfferAndUserNameViewModel> findAllOtherOffers(Long id);

    void deleteOfferByID(Long id);

    void buyOffer(Long offerId, Long userId);
}
