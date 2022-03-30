package io.virtualcave.rates.api.rest.mappers;

import io.virtualcave.api.rest.dtos.v1.RateRequestDto;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface RateRequestDtoMapper {

  RateRequestDtoMapper INSTANCE = Mappers.getMapper(RateRequestDtoMapper.class);

  RateRequest asRateRequest(RateRequestDto rateDto);

  RateRequestDto asRateRequestDto(RateRequest rate);
}