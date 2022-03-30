package io.virtualcave.rates.application.queries;

import io.virtualcave.rates.model.Amount;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.repositories.RateRepository;
import io.virtualcave.rates.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindRateByIdQueryHandler implements QueryHandler<FindRateByIdQuery, Mono<Rate>>{

  private final RateRepository rateRepository;

  private final CurrencyService currencyService;

  @Override
  public Mono<Rate> execute(FindRateByIdQuery query) {
   return this.rateRepository.findById(query.getRateId())
       .zipWhen(this::getAmount, (rateResult, amountResult) -> {
         rateResult.setAmount(amountResult);
         return rateResult;
       });
  }

  private Mono<Amount> getAmount(Rate rate) {
    return currencyService.getAmountByCurrencyCode(rate.getAmount().getCode());
  }
}
