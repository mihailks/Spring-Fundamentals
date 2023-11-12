package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.ExchangeRatesDTO;
import com.softuni.mobilelele.model.entity.ExchangeRateEntity;
import com.softuni.mobilelele.repository.ExchangeRateRepository;
import com.softuni.mobilelele.service.CurrencyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
public class CurrencyServiceImplTestIT {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @BeforeEach
    void setUp() {
        exchangeRateRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        exchangeRateRepository.deleteAll();
    }

    @ParameterizedTest(name = "Testing conversion from BGN {0} to EUR {1} -> expected value {2}")
    @MethodSource("testData_BGN_TO_EUR")
    void test_BGN_TO_EUR(Double exChangeRateBGN, Double exChangeRateEUR, Double expectedValue) {
        var testExchangeRates =
                new ExchangeRatesDTO("USD", Map.of("BGN", BigDecimal.valueOf(exChangeRateBGN),
                        "EUR", BigDecimal.valueOf(exChangeRateEUR)));

        currencyService.refreshRates(testExchangeRates);

        Optional<ExchangeRateEntity> exchangeRateUSD_BGN = exchangeRateRepository.findById("EUR");

        Assertions.assertTrue(exchangeRateUSD_BGN.isPresent());

        Assertions.assertEquals(BigDecimal.valueOf(expectedValue).setScale(2, RoundingMode.DOWN),
                exchangeRateUSD_BGN.map(ExchangeRateEntity::getRate).orElseThrow());
    }

    private static Stream<Arguments> testData_BGN_TO_EUR() {
        return Stream.of(
                Arguments.of(1.840515, 0.937668, 0.51),
                Arguments.of(4.223423, 1312.544, 310.78), //310.777300781854
                Arguments.of(0.223423, 1.0, 4.47), //4.475814934004109
                Arguments.of(50.23, 24.0, 0.48), //0.4778021102926538
                Arguments.of(1.0, 1.0, 1.0)
        );
    }

    @ParameterizedTest(name = "Testing conversion from BGN to USD with exchange rate {0} and expected value {1}")
    @MethodSource("testData_BGN_TO_USD")
    void test_BGN_TO_USD(Double exChangeRate, Double expectedValue) {
        var testExchangeRates = new ExchangeRatesDTO("USD", Map.of("BGN", BigDecimal.valueOf(exChangeRate)));

        currencyService.refreshRates(testExchangeRates);

        Optional<ExchangeRateEntity> exchangeRateUSD_BGN = exchangeRateRepository.findById("USD");

        Assertions.assertTrue(exchangeRateUSD_BGN.isPresent());

        Assertions.assertEquals(BigDecimal.valueOf(expectedValue).setScale(2, RoundingMode.DOWN),
                exchangeRateUSD_BGN.map(ExchangeRateEntity::getRate).orElseThrow());
    }

    private static Stream<Arguments> testData_BGN_TO_USD() {
        return Stream.of(
                Arguments.of(1.830246, 0.55),
                Arguments.of(1.154216, 0.87), //0.8663889601253145
                Arguments.of(1.00, 1.00),
                Arguments.of(2.998763, 0.33), //0.3334708344740815
                Arguments.of(1.123456, 0.89) //0.8901105161216817
        );
    }


}
