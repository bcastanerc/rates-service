package io.virtualcave.rates.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RateRequest {

  private Integer brandId;

  private Integer productId;

  private LocalDate endDate;

  private String currencyCode;

  private Integer totalAmount;
}
