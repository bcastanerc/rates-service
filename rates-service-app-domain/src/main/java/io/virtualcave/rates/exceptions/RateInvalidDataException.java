package io.virtualcave.rates.exceptions;

public class RateInvalidDataException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RateInvalidDataException(String data) {
    super(String.format("The rate data:'%s' is invalid.", data));
  }
}
