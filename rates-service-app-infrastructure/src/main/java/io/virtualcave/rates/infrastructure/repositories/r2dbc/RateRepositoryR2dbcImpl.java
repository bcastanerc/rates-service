package io.virtualcave.rates.infrastructure.repositories.r2dbc;

import io.virtualcave.rates.model.Rate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RateRepositoryR2dbcImpl {

  public Mono<Rate> save(Mono<Rate> rateData) {
    return null;
  }

}
