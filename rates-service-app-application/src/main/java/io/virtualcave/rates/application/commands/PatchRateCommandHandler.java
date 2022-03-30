package io.virtualcave.rates.application.commands;

import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import io.virtualcave.rates.repositories.RateRepository;
import io.virtualcave.rates.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class PatchRateCommandHandler implements CommandReturnHandler<Rate, Mono<Rate>>{

  private final RateRepository rateRepository;

  @Override
  public void execute(Rate rate) {
    this.executeAndReturn(rate);
  }

  @Override
  public Mono<Rate> executeAndReturn(Rate rate) {
    return rateRepository.findById(rate.getId().toString()).flatMap( rateResponse -> {
      rateResponse.getAmount().setValue(rate.getAmount().getValue());
      return rateRepository.patch(rateResponse);
    });
  }
}
