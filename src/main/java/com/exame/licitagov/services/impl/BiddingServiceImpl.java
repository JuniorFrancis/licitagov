package com.exame.licitagov.services.impl;

import com.exame.licitagov.handlers.HttpClientHandler;
import com.exame.licitagov.handlers.UserHandler;
import com.exame.licitagov.models.Bidding;
import com.exame.licitagov.models.VisualizedBidding;
import com.exame.licitagov.models.responses.gov.EntireResponseBidding;
import com.exame.licitagov.repositorys.BiddingRepository;
import com.exame.licitagov.repositorys.UserRepository;
import com.exame.licitagov.repositorys.VisualizedBiddingRepository;
import com.exame.licitagov.services.BiddingService;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import static com.exame.licitagov.handlers.ObjectMapperHandler.parseResponseBodyToObject;
import static com.exame.licitagov.validators.Validators.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    public BiddingServiceImpl(HttpClientHandler httpClientHandler,
                              BiddingRepository biddingRepository,
                              UserRepository userRepository,
                              VisualizedBiddingRepository visualizedBiddingRepository,
                              UserHandler userHandler) {
        this.httpClientHandler = httpClientHandler;
        this.biddingRepository = biddingRepository;
        this.userRepository = userRepository;
        this.visualizedBiddingRepository = visualizedBiddingRepository;
        this.userHandler = userHandler;
    }

    private final BiddingRepository biddingRepository;

    private final UserRepository userRepository;

    private final VisualizedBiddingRepository visualizedBiddingRepository;

    private final UserHandler userHandler;

   private final HttpClientHandler httpClientHandler;

    @Override
    public Page<Bidding> getBids(Optional<String> optionalPublicationDate, int page, int size) throws IOException {
        String publicationDate;
        Page<Bidding> paginatedBidding;

        publicationDate = optionalPublicationDate.orElseGet(this::getDefaultPublicationDate);

        isValidDate(publicationDate);
        boolean hasStoredData = biddingRepository.existsByPublicationDate(publicationDate) ;

        if(!hasStoredData){
            List<Bidding> bids = findExternalBids(publicationDate);
            save(bids);
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        paginatedBidding = biddingRepository.findAllByPublicationDate(publicationDate, pageRequest);

        paginatedBidding.forEach( bidding -> {
            if(visualizedBiddingRepository.existsByBiddingId(bidding.getId())){
                bidding.setVisualisation(true);
            }
        });

        return paginatedBidding;
    }

    private List<Bidding> findExternalBids(String publicationDate) throws IOException {
        EntireResponseBidding entireResponseBidding = null;
        final String OFFSET = "450";

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
            biddingRepository.saveAll(bids);
        }

        System.out.println("LOGGING: DADOS SALVOS.");
    }

    private static List<Bidding> getBidsFromParent(@NotNull EntireResponseBidding entireResponseBidding) {
        return entireResponseBidding.embedded().licitacoes();
    }

    public String getDefaultPublicationDate(){
        LocalDate date = LocalDate.now();
        date = date.minusYears(1).minusDays(1);

        String year = String.valueOf(date.getYear());
        String month = date.getMonthValue() <= 9 ?
                String.valueOf( "0" + date.getMonthValue()) : String.valueOf(date.getMonthValue());
        String day = String.valueOf(date.getDayOfMonth());

        return "20220622";
    }

    @Override
    public void setBiddingAsVisualized(Long biddingId) {

        Long userId = userHandler.getCurrentUser().getId();
        if( biddingRepository.existsById(biddingId) && userRepository.existsById(userId)){

            visualizedBiddingRepository.save(
                    new VisualizedBidding.Builder()
                            .withBidding(biddingId)
                            .withUserId(userId)
                            .build()
            );
        } else {
            throw new IllegalArgumentException("The received id is not valid");
        }
    }
}
