package com.softuni.mobilelele.testUtils;

import com.softuni.mobilelele.model.entity.ExchangeRateEntity;
import com.softuni.mobilelele.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class testData {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;


    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(
                new ExchangeRateEntity().setCurrency(currency).setRate(rate)
        );
    }

    public void cleanAllTestData(){
        exchangeRateRepository.deleteAll();
    }

}
