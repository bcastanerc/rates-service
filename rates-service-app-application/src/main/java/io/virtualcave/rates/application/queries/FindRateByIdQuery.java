package io.virtualcave.rates.application.queries;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindRateByIdQuery implements Query {
  private String rateId;
}
