package com.exame.licitagov.models.responses.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"_links", "count", "offset" })
public record EntireResponseBidding(@JsonProperty("_embedded") BiddingListResponse embedded) {

}
