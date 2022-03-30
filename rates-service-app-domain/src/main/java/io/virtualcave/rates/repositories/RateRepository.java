package io.virtualcave.rates.repositories;

import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import java.time.LocalDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RateRepository {

  Mono<Rate> save(RateRequest rateData);

  Mono<Rate> findById(String id);

  Mono<Rate> patch(Rate rate);

  Flux<Rate> findByProductAndBrandId(String productId, String brandId, LocalDate date);

  Mono<Void> delete(String id);

}
