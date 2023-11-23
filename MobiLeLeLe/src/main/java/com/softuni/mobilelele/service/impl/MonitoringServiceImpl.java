package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.service.MonitoringService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {
    private final Logger LOGGER = LoggerFactory.getLogger(MonitoringServiceImpl.class);

    private final Counter offerSearches;

    private MonitoringServiceImpl(MeterRegistry meterRegistry) {
        this.offerSearches = Counter.builder("offer_search_cnt")
                .description("Counts the number of offer searches")
                .register(meterRegistry);
    }


    @Override
    public void logOfferSearch() {
        LOGGER.info("Offer search was performed!");
        offerSearches.increment();
    }
}
