package io.virtualcave.rates.application.queries;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindRatesByProductBrandIdQuery implements Query {

  private String brandId;

  private String productId;

  private LocalDate startDate;
}
