package io.virtualcave.rates.application.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRateAmountCommand {
  private String id;

  private Integer price;

}
