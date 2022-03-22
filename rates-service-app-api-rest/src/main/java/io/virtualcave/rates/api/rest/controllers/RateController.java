package io.virtualcave.rates.api.rest.controllers;

import io.virtualcave.api.rest.controllers.v1.RatesApi;
import io.virtualcave.api.rest.dtos.v1.RateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*",
    methods = {
        RequestMethod.GET,
        RequestMethod.DELETE,
        RequestMethod.POST,
        RequestMethod.PUT
    })
public class RateController implements RatesApi {

    @Override
    public Mono<ResponseEntity<RateDto>> addRate(Mono<RateDto> rateDto, ServerWebExchange exchange) {
        return Mono.empty();
    }

}
