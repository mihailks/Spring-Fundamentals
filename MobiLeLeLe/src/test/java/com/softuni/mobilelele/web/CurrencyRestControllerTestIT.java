package com.softuni.mobilelele.web;

import com.softuni.mobilelele.testUtils.testData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyRestControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private testData testData;


    @Test
    public void testConvert() throws Exception {

        testData.createExchangeRate("EUR", BigDecimal.valueOf(0.54));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/convert")
                        .param("target", "EUR")
                        .param("amount", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andExpect(jsonPath("$.amount", is(540.0)));

    }





}
