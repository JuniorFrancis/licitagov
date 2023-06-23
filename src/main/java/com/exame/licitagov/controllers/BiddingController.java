package com.exame.licitagov.controllers;

import com.exame.licitagov.models.Bidding;
import com.exame.licitagov.services.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bidding")
public class BiddingController {

    @Autowired
    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    private final BiddingService biddingService;

    @GetMapping
    public List<Bidding> getBids(@RequestParam("publicationDate") Optional<String> optionalPublicationDate) throws IOException {
        return biddingService.getBids(optionalPublicationDate);
    }
}
