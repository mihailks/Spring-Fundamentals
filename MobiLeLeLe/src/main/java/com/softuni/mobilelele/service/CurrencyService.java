package com.softuni.mobilelele.service;

import com.softuni.mobilelele.model.dto.ConvertRequestDTO;
import com.softuni.mobilelele.model.dto.ExchangeRatesDTO;
import com.softuni.mobilelele.model.dto.MoneyDTO;

public interface CurrencyService {
    void refreshRates(ExchangeRatesDTO exchangeRatesDTO);

    MoneyDTO convert(ConvertRequestDTO convertRequestDTO);
}
