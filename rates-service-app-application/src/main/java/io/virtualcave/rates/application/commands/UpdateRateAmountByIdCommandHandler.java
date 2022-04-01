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
public class UpdateRateAmountByIdCommandHandler implements CommandReturnHandler<UpdateRateAmountCommand, Mono<Rate>>{

  private final RateRepository rateRepository;

  @Override
  public void execute(UpdateRateAmountCommand command) {
    this.executeAndReturn(command);
  }

  @Override
  public Mono<Rate> executeAndReturn(UpdateRateAmountCommand command) {
    return rateRepository.findById(command.getId()).flatMap( rateResponse -> {
      rateResponse.getAmount().setValue(command.getPrice());
      return rateRepository.patch(rateResponse);
    });
  }
}
