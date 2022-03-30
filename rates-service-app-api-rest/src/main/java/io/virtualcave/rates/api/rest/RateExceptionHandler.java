package io.virtualcave.rates.api.rest;

import io.virtualcave.rates.exceptions.CurrencyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.handler.WebFluxResponseStatusExceptionHandler;

@Slf4j
@ControllerAdvice
public class RateExceptionHandler extends WebFluxResponseStatusExceptionHandler {

  @ExceptionHandler(value
      = {CurrencyNotFoundException.class})
  protected int handleContractNotFoundException(
      CurrencyNotFoundException ex) {
    return determineRawStatusCode(ex);
  }
}
