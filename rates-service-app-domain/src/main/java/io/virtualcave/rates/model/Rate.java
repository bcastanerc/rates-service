package io.virtualcave.rates.model;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "myschema.t_rates")
public class Rate {

  @Id
  private Integer id;

  private Integer brandId;

  private Integer productId;

  private LocalDate startDate;

  private LocalDate endDate;

  private Amount amount;
}
