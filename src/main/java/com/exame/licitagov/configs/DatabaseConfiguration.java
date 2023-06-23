package com.exame.licitagov.configs;

import com.exame.licitagov.services.impl.BiddingServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Optional;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    public DatabaseConfiguration(Environment environment, BiddingServiceImpl biddingService) {
        this.environment = environment;
        this.biddingService = biddingService;
    }

    private final Environment environment;

    private final BiddingServiceImpl biddingService;

    @PostConstruct
    public void populateInitialBids() throws IOException {
        String profile = "";

        for (String profileName : environment.getActiveProfiles()) {
            profile = profileName;
        }

        if(!profile.isBlank()){
            biddingService.getBids(Optional.empty(), 1, 10);
        };
    }



}
