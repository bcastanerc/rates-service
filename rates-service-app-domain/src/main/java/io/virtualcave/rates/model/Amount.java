package io.virtualcave.rates.model;

import io.virtualcave.rates.validation.CurrencyIsoCode;
import io.virtualcave.rates.validation.SelfValidator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Amount extends SelfValidator {

  private static String DEFAULT_CURRENCY = "EUR";

  private static String DEFAULT_CURRENCY_SEPARATOR = ".";

  private Integer value;

  @CurrencyIsoCode
  private String currency;

  private Integer exponent = 0;

  public BigDecimal toBigDecimal() {
    BigDecimal val = new BigDecimal(value);
    return val.divide(BigDecimal.TEN.pow(exponent), exponent, RoundingMode.HALF_UP);
  }

  public static Amount from(String value) {
    Amount amount = new Amount();
    amount.setCurrency(DEFAULT_CURRENCY);
    if (value.length() > 9) {
      value = value.substring(0, 9);
    }
    amount.setValue(getInt(value));
    amount.setExponent(calculateExponent(value));
    return amount;
  }

  private static int getInt(String value) {
    return Integer.parseInt(value.replace(DEFAULT_CURRENCY_SEPARATOR, ""));
  }

  private static int calculateExponent(String value) {
    return value.length() - (value.indexOf(DEFAULT_CURRENCY_SEPARATOR) + 1);
  }

}
