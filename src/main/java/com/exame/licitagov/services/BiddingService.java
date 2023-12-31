package com.exame.licitagov.services;

import com.exame.licitagov.models.Bidding;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BiddingService {

    void save(List<Bidding> bids);
    Page<Bidding> getBids(Optional<String> optionalPublicationDate, int page, int size) throws IOException;

    void setBiddingAsVisualized(Long biddingId);
}
