package com.softuni.mobilelele.web;

import com.softuni.mobilelele.model.entity.OfferEntity;
import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.testUtils.UserTestDataUtil;
import com.softuni.mobilelele.testUtils.testData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTestIT {
    private static final String TEST_USER1_NAME = "user1@example.com";
    private static final String TEST_USER2_NAME = "user2@example.com";
    private static final String TEST_ADMIN_NAME = "admin@example.com";
    @Autowired
    private UserTestDataUtil userTestDataUtil;
    @Autowired
    private testData testData;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        testData.cleanUp();
        userTestDataUtil.cleanUp();
    }

    @AfterEach
    void tearDown() {
        testData.cleanUp();
        userTestDataUtil.cleanUp();
    }

    @Test
    void testAnonymousUserDeletionFails() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser("anonymousUse@example.com");
        OfferEntity testOffer = testData.createTestOffer(owner);
        mockMvc.perform(
                        delete("/offer/{uuid}", testOffer.getUuid())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));

    }

    @Test
    @WithMockUser(username = TEST_USER1_NAME)
    void testNonAdminUserOwnOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER1_NAME);
        OfferEntity testOffer = testData.createTestOffer(owner);
        mockMvc.perform(
                        delete("/offer/{uuid}", testOffer.getUuid())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));

    }

    @Test
    @WithMockUser(username = TEST_USER2_NAME)
    void testNonAdminUserNotOwnOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER1_NAME);
        OfferEntity testOffer = testData.createTestOffer(owner);
        mockMvc.perform(
                        delete("/offer/{uuid}", testOffer.getUuid())
                                .with(csrf())
                )
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = TEST_ADMIN_NAME, roles = {"ADMIN", "USER"})
    void testAdminUserNotOwnOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER1_NAME);
        userTestDataUtil.createTestAdmin(TEST_ADMIN_NAME);
        OfferEntity testOffer = testData.createTestOffer(owner);
        mockMvc.perform(
                        delete("/offer/{uuid}", testOffer.getUuid())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));
    }


}
