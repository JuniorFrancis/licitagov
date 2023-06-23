package com.exame.licitagov.controllers;

import com.exame.licitagov.models.Bidding;
import com.exame.licitagov.services.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bidding")
public class BiddingController {

    @Autowired
    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    private final BiddingService biddingService;

    @GetMapping("/{publicationDate}")
    public List<Bidding> getBids(@PathVariable String publicationDate) throws IOException {
        return biddingService.getBids(publicationDate);
    }
}
