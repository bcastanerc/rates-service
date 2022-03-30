package io.virtualcave.rates.infrastructure.repositories.repositories.entitydb;

import java.time.LocalDate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RateRepositoryR2dbc extends ReactiveCrudRepository<RateEntity, String> {

  Flux<RateEntity> findByProductIdAndBrandIdAndStartDateAfter(String productId, String brandId, LocalDate date);
}
