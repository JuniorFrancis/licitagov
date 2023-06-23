package com.exame.licitagov.models.responses.gov;

import com.exame.licitagov.models.Bidding;

import java.util.List;

public record BiddingListResponse(List<Bidding> licitacoes) {

}
