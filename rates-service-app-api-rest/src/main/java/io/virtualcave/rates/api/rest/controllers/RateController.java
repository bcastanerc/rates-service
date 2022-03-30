package io.virtualcave.rates.api.rest.controllers;

import io.virtualcave.api.rest.controllers.v1.RatesApi;
import io.virtualcave.api.rest.dtos.v1.RateDto;
import io.virtualcave.api.rest.dtos.v1.RateRequestDto;
import io.virtualcave.rates.api.rest.mappers.RateDtoMapper;
import io.virtualcave.rates.api.rest.mappers.RateRequestDtoMapper;
import io.virtualcave.rates.application.commands.AddRateCommandHandler;
import io.virtualcave.rates.application.commands.DeleteRateCommandHandler;
import io.virtualcave.rates.application.queries.FindRateByIdQuery;
import io.virtualcave.rates.application.queries.FindRateByIdQueryHandler;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
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

    private final DeleteRateCommandHandler deleteRateCommandHandler;

    private final FindRateByIdQueryHandler findRateByIdQueryHandler;

    private final RateDtoMapper rateDtoMapper;

    private final RateRequestDtoMapper rateRequestDtoMapper;

    @Override
    public Mono<ResponseEntity<RateDto>> addRate(Mono<RateRequestDto> rateRequestDto, ServerWebExchange exchange) {
        return rateRequestDto.map(rateRequestDtoMapper::asRateRequest)
            .flatMap(addRateCommandHandler::executeAndReturn)
            .map(rateDtoMapper::asRateDto)
            .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<RateDto>> findRateById(String id, ServerWebExchange exchange) {
        return findRateByIdQueryHandler.execute(FindRateByIdQuery.builder().rateId(id).build())
            .map(rateDtoMapper::asRateDto)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteById(String id, ServerWebExchange exchange) {
        return deleteRateCommandHandler.executeAndReturn(id)
            .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<RateDto>> updateRateById(String id, String price, ServerWebExchange exchange) {
        return Mono.empty();
    }

    @Override
    public Mono<ResponseEntity<Flux<RateDto>>> findRateByProductAndBrand(LocalDate startDate, String brandId, String productId,
        ServerWebExchange exchange) {
        return Mono.empty();
    }

}
