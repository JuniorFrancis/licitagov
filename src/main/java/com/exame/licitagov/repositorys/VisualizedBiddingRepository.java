package com.exame.licitagov.repositorys;

import com.exame.licitagov.models.VisualizedBidding;
import org.springframework.data.repository.CrudRepository;

public interface VisualizedBiddingRepository extends CrudRepository<VisualizedBidding, Integer> {

    boolean existsByBiddingId(Long id);
}
