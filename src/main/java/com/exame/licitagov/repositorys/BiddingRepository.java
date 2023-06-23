package com.exame.licitagov.repositorys;

import com.exame.licitagov.models.Bidding;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BiddingRepository extends CrudRepository<Bidding, Long> {

    boolean existsByPublicationDate(String publicationDate);
}
