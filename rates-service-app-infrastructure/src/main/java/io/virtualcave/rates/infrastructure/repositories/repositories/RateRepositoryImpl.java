package io.virtualcave.rates.infrastructure.repositories.repositories;

import io.virtualcave.rates.infrastructure.repositories.repositories.entitydb.RateEntityMapper;
import io.virtualcave.rates.infrastructure.repositories.repositories.entitydb.RateRepositoryR2dbc;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import io.virtualcave.rates.repositories.RateRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class RateRepositoryImpl implements RateRepository {

  private final RateRepositoryR2dbc rateRepositoryR2dbc;

  private final RateEntityMapper rateEntityMapper;


  @Override
  public Mono<Rate> save(RateRequest rateData) {
    return rateRepositoryR2dbc.save(rateEntityMapper.asRateRequestToRateEntity(rateData))
        .map(rateEntityMapper::asRateFromRateEntity);
  }

  @Override
  public Mono<Rate> findById(String id) {
    return rateRepositoryR2dbc.findById(id)
        .map(rateEntityMapper::asRateFromRateEntity);
  }

  @Override
  public Mono<Rate> patch(Rate rate) {
    return rateRepositoryR2dbc.save(rateEntityMapper.asRateEntityFromRate(rate))
        .map(rateEntityMapper::asRateFromRateEntity);
  }

  @Override
  public Flux<Rate> findByProductAndBrandId(String productId, String brandId, LocalDate date) {
    return rateRepositoryR2dbc.findByProductIdAndBrandIdAndStartDateAfter(productId, brandId, date)
        .map(rateEntityMapper::asRateFromRateEntity);
  }

  @Override
  public Mono<Void> delete(String id) {
    return rateRepositoryR2dbc.deleteById(id);
  }


}
