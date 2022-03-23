package io.virtualcave.rates.infrastructure.repositories;

import io.virtualcave.rates.infrastructure.repositories.r2dbc.RateRepositoryR2dbcImpl;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RateRepositoryImpl implements RateRepository {


  private final RateRepositoryR2dbcImpl rateRepositoryR2dbc;

  @Override
  public Mono<Rate> save(Mono<Rate> rateData) {
    return rateRepositoryR2dbc.save(rateData);
  }
}
