package io.virtualcave.rates.api.rest.controllers;

import io.virtualcave.api.rest.controllers.v1.RatesApi;
import io.virtualcave.api.rest.dtos.v1.RateDto;
import io.virtualcave.api.rest.dtos.v1.RateRequestDto;
import io.virtualcave.rates.api.rest.mappers.RateDtoMapper;
import io.virtualcave.rates.api.rest.mappers.RateRequestDtoMapper;
import io.virtualcave.rates.application.commands.AddRateCommandHandler;
import io.virtualcave.rates.application.commands.DeleteRateByIdCommandHandler;
import io.virtualcave.rates.application.commands.UpdateRateAmountByIdCommandHandler;
import io.virtualcave.rates.application.commands.UpdateRateAmountCommand;
import io.virtualcave.rates.application.queries.FindRateByIdQuery;
import io.virtualcave.rates.application.queries.FindRateByIdQueryHandler;
import io.virtualcave.rates.application.queries.FindRatesByProductBrandIdQuery;
import io.virtualcave.rates.application.queries.FindRatesByProductBrandIdQueryHandler;
import java.net.URI;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RateController implements RatesApi {

    private final AddRateCommandHandler addRateCommandHandler;

    private final DeleteRateByIdCommandHandler deleteRateByIdCommandHandler;

    private final UpdateRateAmountByIdCommandHandler updateRateAmountByIdCommandHandler;

    private final FindRateByIdQueryHandler findRateByIdQueryHandler;

    private final FindRatesByProductBrandIdQueryHandler findRatesByProductBrandIdQueryHandler;


    private final RateDtoMapper rateDtoMapper;

    private final RateRequestDtoMapper rateRequestDtoMapper;

    @Override
    public Mono<ResponseEntity<RateDto>> addRate(Mono<RateRequestDto> rateRequestDto, ServerWebExchange exchange) {
        return rateRequestDto.map(rateRequestDtoMapper::asRateRequest)
            .flatMap(addRateCommandHandler::executeAndReturn)
            .map(rateDtoMapper::asRateDto)
            .map(rateDto -> ResponseEntity.created(URI.create(String.format("/v1/rate/%s", rateDto.getId()))).body(rateDto));
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
        return deleteRateByIdCommandHandler.executeAndReturn(id)
            .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<RateDto>> updateRateById(String id, Integer price, ServerWebExchange exchange) {
       return updateRateAmountByIdCommandHandler.executeAndReturn(UpdateRateAmountCommand.builder().id(id).price(price).build())
           .map(rateDtoMapper::asRateDto)
           .map(ResponseEntity::ok)
           .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<RateDto>>> findRateByProductAndBrand(LocalDate startDate, String brandId, String productId,
        ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(findRatesByProductBrandIdQueryHandler.execute(FindRatesByProductBrandIdQuery.builder().productId(productId).brandId(brandId).startDate(startDate).build())
            .map(rateDtoMapper::asRateDto)))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
