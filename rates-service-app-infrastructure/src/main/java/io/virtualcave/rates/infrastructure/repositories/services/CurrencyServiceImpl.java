package io.virtualcave.rates.infrastructure.repositories.services;


import io.virtualcave.clients.rest.controllers.v1.CurrenciesApi;
import io.virtualcave.rates.exceptions.CurrencyNotFoundException;
import io.virtualcave.rates.exceptions.UnavailableCurrencyServiceException;
import io.virtualcave.rates.model.Amount;
import io.virtualcave.rates.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

  private final CurrencyMapper currencyMapper;

  private final CurrenciesApi currenciesApi;

  @Override
  public Mono<Amount> getAmountByCurrencyCode(String currencyCode) {
    return this.currenciesApi.getCurrencyByCode(currencyCode)
        .onErrorResume(error -> {
          if (error instanceof WebClientResponseException && ((WebClientResponseException) error).getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new CurrencyNotFoundException(currencyCode);
          }
          throw new UnavailableCurrencyServiceException(error);
        }).map(currencyMapper::currencyDtoToAmount);
  }

  @Override
  public Flux<Amount> findCurrencies() {
    return currenciesApi.getCurrencies().map(currencyMapper::currencyDtoToAmount);
  }


}
