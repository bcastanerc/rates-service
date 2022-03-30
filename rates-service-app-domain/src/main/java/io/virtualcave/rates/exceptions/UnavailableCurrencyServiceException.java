package io.virtualcave.rates.exceptions;

public class UnavailableCurrencyServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UnavailableCurrencyServiceException(Throwable throwable){
    super("Unavailable currency service", throwable);
  }
}
