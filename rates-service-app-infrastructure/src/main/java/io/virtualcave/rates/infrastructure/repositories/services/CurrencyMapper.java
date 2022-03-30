package io.virtualcave.rates.infrastructure.repositories.services;

import io.virtualcave.clients.rest.dtos.v1.CurrencyDto;
import io.virtualcave.rates.model.Amount;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface CurrencyMapper {
  
  Amount currencyDtoToAmount(CurrencyDto currencyDto);

}
