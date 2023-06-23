package com.exame.licitagov.services;

import com.exame.licitagov.models.Bidding;

import java.io.IOException;
import java.util.List;

public interface BiddingService {

    void save(List<Bidding> bids);
    List<Bidding> getBids(String publicationDate) throws IOException;

}
