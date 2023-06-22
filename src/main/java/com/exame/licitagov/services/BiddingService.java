package com.exame.licitagov.services;

import com.exame.licitagov.models.Bidding;

import java.io.IOException;
import java.util.List;

public interface BiddingService {

    List<Bidding> getBids(String publicationDate) throws IOException;
}
