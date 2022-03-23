package io.virtualcave.rates.application.commands;

import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddRateCommandHandler implements CommandReturnHandler<Rate, Rate>{

  private final RateRepository rateRepository;

  @Override
  public void execute(Mono<Rate> rate) {
    this.executeAndReturn(rate);
  }

  @Override
  public Mono<Rate> executeAndReturn(Mono<Rate> rate) {
    log.debug("Add rate: {}", rate);
    return rateRepository.save(rate);
  }
}
