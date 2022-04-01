package io.virtualcave.rates.application.queries;

import io.virtualcave.rates.model.Amount;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.repositories.RateRepository;
import io.virtualcave.rates.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindRatesByProductBrandIdQueryHandler implements QueryHandler<FindRatesByProductBrandIdQuery, Flux<Rate>> {

  private final RateRepository rateRepository;

  private final CurrencyService currencyService;

  @Override
  public Flux<Rate> execute(FindRatesByProductBrandIdQuery query) {
    return this.rateRepository.findByProductAndBrandId(query.getProductId(), query.getBrandId(), query.getStartDate())
        .flatMap(rate -> getAmount(rate).map(amount ->  {
          rate.setAmount(amount);
          return rate;
        }));
  }

  private Mono<Amount> getAmount(Rate rate) {
    return currencyService.getAmountByCurrencyCode(rate.getAmount().getCode());
  }
}
