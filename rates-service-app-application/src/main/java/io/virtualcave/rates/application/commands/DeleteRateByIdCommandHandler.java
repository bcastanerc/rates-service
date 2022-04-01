package io.virtualcave.rates.application.commands;

import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import io.virtualcave.rates.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteRateByIdCommandHandler implements CommandReturnHandler<String, Mono<Void>> {

  private final RateRepository rateRepository;

  @Override
  public void execute(String rateId) {
    this.executeAndReturn(rateId);
  }

  @Override
  public Mono<Void> executeAndReturn(String rateId) {
    return rateRepository.delete(rateId);
  }
}
