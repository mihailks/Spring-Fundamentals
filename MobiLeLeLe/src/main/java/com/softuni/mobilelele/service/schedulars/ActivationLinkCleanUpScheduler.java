package com.softuni.mobilelele.service.schedulars;

import com.softuni.mobilelele.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivationLinkCleanUpScheduler {

    private final UserActivationService userActivationService;

    public ActivationLinkCleanUpScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void cleanUp(){
        System.out.println("clean up links" + LocalDateTime.now());
        userActivationService.cleanObsoleteActivationLinks();

    }

}
