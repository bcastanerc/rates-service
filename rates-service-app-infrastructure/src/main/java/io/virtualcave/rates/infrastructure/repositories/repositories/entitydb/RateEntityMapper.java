package io.virtualcave.rates.infrastructure.repositories.repositories.entitydb;

import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface RateEntityMapper {

  RateEntity asRateRequestToRateEntity(RateRequest rate);

  @Mapping(source = "price", target = "amount.value")
  @Mapping(source = "currencyCode", target = "amount.code")
  Rate asRateToRateEntity(RateEntity rateEntity);

  RateEntity asRateToRateEntity(Rate rate);
}
