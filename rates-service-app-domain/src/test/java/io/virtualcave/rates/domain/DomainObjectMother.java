package io.virtualcave.rates.domain;

import io.virtualcave.rates.model.Amount;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import java.time.LocalDate;

public class DomainObjectMother {

  private final static String DEFAULT_CODE = "EUR";

  private final static String DEFAULT_SYMBOL = "€";

  private final static Integer DEFAULT_VALUE = 1704;

  private final static LocalDate DEFAULT_END_DATE = LocalDate.of(2022, 12, 31);


  public static Rate rate() {
    Rate rate = new Rate();
    rate.setId(1);
    rate.setBrandId(2);
    rate.setProductId(3);
    rate.setStartDate(LocalDate.of(2022, 1, 1));
    rate.setEndDate(DEFAULT_END_DATE);
    rate.setAmount(amount());
    return rate;
  }

  public static Amount amount() {
    Amount amount = new Amount();
    amount.setDecimals(2);
    amount.setValue(DEFAULT_VALUE);
    amount.setSymbol(DEFAULT_SYMBOL);
    amount.setCode(DEFAULT_CODE);
    return amount;
  }

  public static RateRequest rateRequest() {
    RateRequest rateRequest = new RateRequest();
    rateRequest.setBrandId(2);
    rateRequest.setProductId(3);
    rateRequest.setEndDate(DEFAULT_END_DATE);
    rateRequest.setTotalAmount(DEFAULT_VALUE);
    rateRequest.setCurrencyCode(DEFAULT_CODE);
    return rateRequest;
  }
}
