package com.exame.licitagov.handlers;

import com.exame.licitagov.exceptions.UnexpectedExternalException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.rmi.UnexpectedException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class HttpClientHandler {

    @Value("${http.urls.licitacoes}")
    private String URL_GOV_LICITACOES;

    @Value("${http.configs.read-timeout}")
    private final Integer READ_TIMEOUT = 1;

    @Retryable( retryFor = {UnexpectedExternalException.class, SocketTimeoutException.class})
    public ResponseBody request(Map<String, String> params) throws IOException {
        ResponseBody responseBody = null;

        String parsedUrl = prepareParams(params);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                .build();

        Request request = new Request.Builder()
                .url(parsedUrl)
                .get()
                .build();
        System.out.println("LOGGING: INICIANDO REQUISIÇÃO A API GOV");
        System.out.println("LOGGING: URL: " + parsedUrl);

        try {
            Response response = client.newCall(request).execute();

            if(response.isSuccessful()){
                responseBody = response.body();
                System.out.println("LOGGING: REQUISIÇÃO FEITA COM SUCESSO");
            } else {
                System.out.println("LOGGING: ERRO NA REQUISIÇÃO " + response.code());
                response.close();
                throw new UnexpectedExternalException();
            }
        } catch ( SocketTimeoutException e) {
            System.out.println("LOGGING: ERRO NA REQUISIÇÃO " + responseBody);
            throw new UnexpectedExternalException();
        }

        return responseBody;
    }

    private String prepareParams(Map<String, String> params){
        StringBuilder parsedUrl = new StringBuilder();
        String rawUrl = URL_GOV_LICITACOES;

        params.forEach( (param, value) -> {
             parsedUrl.append(param).append("=").append(value).append("&");
        });

        return rawUrl.concat(parsedUrl.toString());
    }
}