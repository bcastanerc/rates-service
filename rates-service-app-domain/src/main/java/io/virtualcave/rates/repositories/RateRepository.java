package io.virtualcave.rates.repositories;

import io.virtualcave.rates.model.Rate;
import reactor.core.publisher.Mono;

public interface RateRepository {

  Mono<Rate> save(Mono<Rate> rateData);

}
