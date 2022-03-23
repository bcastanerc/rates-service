package io.virtualcave.rates.api.rest.controllers;

import io.virtualcave.api.rest.controllers.v1.RatesApi;
import io.virtualcave.api.rest.dtos.v1.RateDto;
import io.virtualcave.rates.api.rest.mappers.RateDtoMapper;
import io.virtualcave.rates.application.commands.AddRateCommandHandler;
import io.virtualcave.rates.model.Rate;
import java.time.OffsetDateTime;
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

    private final AddRateCommandHandler addRateCommandHandler;

    private final RateDtoMapper rateDtoMapper;

    @Override
    public Mono<ResponseEntity<RateDto>> addRate(Mono<RateDto> rateDto, ServerWebExchange exchange) {
        return addRateCommandHandler.executeAndReturn(rateDto.map(rateDtoMapper::asRate))
            .map(rateDtoMapper::asRateDto)
            .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<RateDto>> findRateById(String id, ServerWebExchange exchange) {
        return Mono.empty();
    }

    @Override
    public Mono<ResponseEntity<RateDto>> updateRateById(String id, Mono<RateDto> body, ServerWebExchange exchange) {
        return Mono.empty();
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteById(String id, ServerWebExchange exchange) {
        return Mono.empty();
    }

}
