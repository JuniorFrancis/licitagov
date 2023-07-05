package com.exame.licitagov.repositorys;

import com.exame.licitagov.models.Bidding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {

    boolean existsByPublicationDate(String publicationDate);

    Page<Bidding> findAllByPublicationDate(String publicationDate, Pageable pageable);
}
