package io.virtualcave.rates.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Rate {

  private String id;

  private String brandId;

  private String productId;

  private LocalDate date;

  private Amount amount;
}
