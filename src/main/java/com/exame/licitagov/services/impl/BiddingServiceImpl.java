package com.exame.licitagov.services.impl;

import com.exame.licitagov.handlers.HttpClientHandler;
import com.exame.licitagov.models.Bidding;
import com.exame.licitagov.models.responses.gov.EntireResponseBidding;
import com.exame.licitagov.repositorys.BiddingRepository;
import com.exame.licitagov.services.BiddingService;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.exame.licitagov.handlers.ObjectMapperHandler.parseResponseBodyToObject;
import static com.exame.licitagov.validators.Validators.isEmptyList;
import static com.exame.licitagov.validators.Validators.isNullValue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    public BiddingServiceImpl(HttpClientHandler httpClientHandler, BiddingRepository biddingRepository) {
        this.httpClientHandler = httpClientHandler;
        this.biddingRepository = biddingRepository;
    }

    private final BiddingRepository biddingRepository;

   private final HttpClientHandler httpClientHandler;

    private final String OFFSET = "450";

    @Override
    public List<Bidding> getBids(String publicationDate) throws IOException {
        //TODO Fazer conulsta no DB primeiro. Se não houver registros, fazer chamada na api, salvar no db e devolver lista

        List<Bidding> bids = biddingRepository.findByPublicationDate(publicationDate);

        if(isEmptyList(bids)){
            bids = queryBiddingAPI(publicationDate);
        };


        return bids;
    }

    private List<Bidding> queryBiddingAPI(String publicationDate) throws IOException {
        EntireResponseBidding entireResponseBidding = null;

        Map<String, String> params = Map.of(
                "data_publicacao", publicationDate,
                "offset", OFFSET
        );

        ResponseBody response = httpClientHandler.request(params);

        if(!isNullValue(response)){
            entireResponseBidding = parseResponseBodyToObject(response, EntireResponseBidding.class);
        }

        return getBids(entireResponseBidding);
    }

    private static List<Bidding> getBids(EntireResponseBidding entireResponseBidding) {
        return entireResponseBidding.embedded().licitacoes();
    }



}
