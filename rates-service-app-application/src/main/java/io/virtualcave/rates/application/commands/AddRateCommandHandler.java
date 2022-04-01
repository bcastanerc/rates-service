package io.virtualcave.rates.application.commands;

import io.virtualcave.rates.exceptions.RateInvalidDataException;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import io.virtualcave.rates.repositories.RateRepository;
import io.virtualcave.rates.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddRateCommandHandler implements CommandReturnHandler<RateRequest, Mono<Rate>> {

  private final RateRepository rateRepository;

  private final CurrencyService currencyService;

  @Override
  public void execute(RateRequest rate) {
    this.executeAndReturn(rate);
  }

  @Override
  public Mono<Rate> executeAndReturn(RateRequest rate) {
    if (ObjectUtils.isEmpty(rate)) throw new RateInvalidDataException("Error in rate request, data is null.");

    return rateRepository.save(rate)
        .zipWith(currencyService.getAmountByCurrencyCode(rate.getCurrencyCode()), (resultRate, resultAmount) -> {
      resultRate.setAmount(resultAmount);
      return resultRate;
    });
  }
}
