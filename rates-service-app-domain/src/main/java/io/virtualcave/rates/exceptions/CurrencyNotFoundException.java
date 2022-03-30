package io.virtualcave.rates.exceptions;

public class CurrencyNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CurrencyNotFoundException(String currencyCode){
    super(String.format("Currency not found matching '%s' code.", currencyCode));
  }
}
