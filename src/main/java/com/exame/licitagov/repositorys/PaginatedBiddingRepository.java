package com.exame.licitagov.repositorys;

import com.exame.licitagov.models.Bidding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PaginatedBiddingRepository extends PagingAndSortingRepository<Bidding, Long> {

    Page<Bidding> findAllByPublicationDate(String publicationDate, Pageable pageable);

}
