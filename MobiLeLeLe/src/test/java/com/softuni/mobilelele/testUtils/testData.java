package com.softuni.mobilelele.testUtils;

import com.softuni.mobilelele.model.entity.ExchangeRateEntity;
import com.softuni.mobilelele.model.entity.OfferEntity;
import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import com.softuni.mobilelele.repository.ExchangeRateRepository;
import com.softuni.mobilelele.repository.OfferRepository;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.repository.UserRoleRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class testData {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;



    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(
                new ExchangeRateEntity().setCurrency(currency).setRate(rate)
        );
    }

    public void cleanAllTestData() {
        exchangeRateRepository.deleteAll();
    }

    public String createOffer(UserEntity owner) {
        return null;
    }
}
