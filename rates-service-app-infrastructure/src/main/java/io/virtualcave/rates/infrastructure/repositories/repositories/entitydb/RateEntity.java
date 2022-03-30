package io.virtualcave.rates.infrastructure.repositories.repositories.entitydb;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "public.t_rates")
public class RateEntity {

  @Id
  private Integer id;

  private Integer brandId;

  private Integer productId;

  private Integer price;

  private LocalDate startDate = LocalDate.now();

  private LocalDate endDate;

  private String currencyCode;
}
