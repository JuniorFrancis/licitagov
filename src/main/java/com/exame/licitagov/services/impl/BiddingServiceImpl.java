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
import static com.exame.licitagov.validators.Validators.*;

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

    private static List<Bidding> getBidsFromParent(EntireResponseBidding entireResponseBidding) {
        return entireResponseBidding.embedded().licitacoes();
    }

    private List<Bidding> findExternalBids(String publicationDate) throws IOException {
        EntireResponseBidding entireResponseBidding = null;

        Map<String, String> params = Map.of(
                "data_publicacao", publicationDate,
                "offset", OFFSET
        );

        ResponseBody response = httpClientHandler.request(params);

        if(!isNullValue(response)){
            entireResponseBidding = parseResponseBodyToObject(response, EntireResponseBidding.class);
        } else {
            return List.of();
        }

        return getBidsFromParent(entireResponseBidding);
    }

    @Override
    public void save(List<Bidding> bids) {
        System.out.println("LOGGING: " + bids.size() + " RETORNADOS PELA API");

        if(!bids.isEmpty()){
            System.out.println("LOGGING: SALVANDO RETORNO DA API NO BANCO.");
            bids.forEach(biddingRepository::save);
        }

        System.out.println("LOGGING: DADOS SALVOS.");
    }

    @Override
    public List<Bidding> getBids(String publicationDate) throws IOException {
        System.out.println("LOGGING: BUSCA DE DADOS NO PERIODO " + publicationDate);
        isValidDate(publicationDate);

        System.out.println("LOGGING: BUSCANDO DADOS NA BANCO");
        List<Bidding> bids = biddingRepository.findByPublicationDate(publicationDate);

        if(isEmptyList(bids)){
            System.out.println("LOGGING: DADOS NO BANCO NÃO ENCONTRADOS.");
            bids = findExternalBids(publicationDate);
            save(bids);
        };

        return bids;
    }




}
