package com.exame.licitagov.services.impl;

import com.exame.licitagov.models.request.RegisterRequest;
import com.exame.licitagov.repositorys.BiddingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BiddingServiceImplTest {

    @InjectMocks
    BiddingServiceImpl biddingService;

    @Mock
    BiddingRepository biddingRepository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCallWithInvalidDate() throws IOException {
        String INVALID_DATE = "11112233";

        assertThrows(IllegalArgumentException.class, () ->
            biddingService.getBids(Optional.of(INVALID_DATE), 0, 10)
        );
    }

    @Test
    void testWithDefaultDateIsValid() throws IOException {
        String defaultDate = biddingService.getDefaultPublicationDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parsedDate = LocalDate.parse(defaultDate, formatter);

        assertInstanceOf(LocalDate.class, parsedDate);
    }
}