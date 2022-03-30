package io.virtualcave.rates.services;

import io.virtualcave.rates.model.Amount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyService {
  Mono<Amount> getAmountByCurrencyCode(String currencyCode);
  Flux<Amount> findCurrencies();
}
