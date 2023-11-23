package com.softuni.mobilelele.testUtils;

import com.softuni.mobilelele.model.entity.*;
import com.softuni.mobilelele.model.entity.enums.EngineEnum;
import com.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import com.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import com.softuni.mobilelele.repository.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class testData {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private BrandRepository brandRepository;

    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(
                new ExchangeRateEntity().setCurrency(currency).setRate(rate)
        );
    }

    public OfferEntity createTestOffer(UserEntity owner) {
        //create test brand
        BrandEntity brandEntity = brandRepository.save(new BrandEntity()
                .setName("test brand")
                .setModels(List.of(
                        new ModelEntity().setName("test model1"),
                        new ModelEntity().setName("test model2"))));

        //create test offer
        OfferEntity offerEntity = new OfferEntity()
                .setModel(brandEntity.getModels().get(0))
                .setImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Ftoyota%2Fcamry%2F2019%2F&psig=AOvVaw0QZ4Z4Z4Z4Z4Z4Z4Z4Z4Z4&ust=1629789855554000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJjQ4ZqHgvICFQAAAAAdAAAAABAD")
                .setEngine(EngineEnum.DIESEL)
                .setMileage(100000)
                .setPrice(BigDecimal.valueOf(10000))
                .setYear(2019)
                .setUuid(UUID.randomUUID())
                .setDescription("testDescription")
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setSeller(owner);
        return offerRepository.save(offerEntity);
    }

    public void cleanAllTestData() {
        exchangeRateRepository.deleteAll();
        offerRepository.deleteAll();
    }

    public void cleanUp() {
        exchangeRateRepository.deleteAll();
        offerRepository.deleteAll();
        brandRepository.deleteAll();
    }
}
