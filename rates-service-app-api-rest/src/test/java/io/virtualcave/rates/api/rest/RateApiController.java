package io.virtualcave.rates.api.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.virtualcave.api.rest.dtos.v1.RateRequestDto;
import io.virtualcave.rates.api.rest.controllers.RateController;
import io.virtualcave.rates.api.rest.mappers.RateDtoMapperImpl;
import io.virtualcave.rates.api.rest.mappers.RateRequestDtoMapperImpl;
import io.virtualcave.rates.application.commands.AddRateCommandHandler;
import io.virtualcave.rates.application.commands.DeleteRateByIdCommandHandler;
import io.virtualcave.rates.application.commands.UpdateRateAmountByIdCommandHandler;
import io.virtualcave.rates.application.commands.UpdateRateAmountCommand;
import io.virtualcave.rates.application.queries.FindRateByIdQuery;
import io.virtualcave.rates.application.queries.FindRateByIdQueryHandler;
import io.virtualcave.rates.domain.DomainObjectMother;
import io.virtualcave.rates.model.Rate;
import io.virtualcave.rates.model.RateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@ContextConfiguration(classes = {RateController.class, RateExceptionHandler.class})
public class RateApiController {

  @MockBean
  private AddRateCommandHandler addRateCommandHandler;

  @MockBean
  private  UpdateRateAmountByIdCommandHandler updateRateAmountByIdCommandHandler;

  @MockBean
  private DeleteRateByIdCommandHandler deleteRateByIdCommandHandler;

  @MockBean
  private  FindRateByIdQueryHandler findRateByIdQueryHandler;

  @SpyBean
  private RateDtoMapperImpl rateDtoMapper;

  @SpyBean
  private RateRequestDtoMapperImpl rateRequestDtoMapper;

  @Autowired
  private WebTestClient testClient;

  @Test
  void addRate_ShouldReturnNewRate_WhenSuccess() {
    RateRequest rateRequest = DomainObjectMother.rateRequest();
    RateRequestDto rateRequestDto = rateRequestDtoMapper.asRateRequestDto(rateRequest);

    given(addRateCommandHandler.executeAndReturn(any())).willReturn(Mono.just(DomainObjectMother.rate()));

    testClient.post().uri("/v1/rates/")
    .contentType(APPLICATION_JSON)
    .accept(APPLICATION_JSON)
    .body(Mono.just(rateRequestDto), RateRequestDto.class)
    .exchange()
    .expectStatus().isCreated()
    .expectHeader().contentType(APPLICATION_JSON)
    .expectBody(Rate.class)
    .consumeWith(response -> {
      Rate responseBody = response.getResponseBody();
      assertThat(responseBody).isNotNull();
      assertThat(responseBody.getAmount().getValue()).isEqualTo(rateRequest.getTotalAmount());
      assertThat(responseBody.getBrandId()).isEqualTo(rateRequest.getBrandId());
      assertThat(responseBody.getProductId()).isEqualTo(rateRequest.getProductId());
    });
  }

  @Test
  void findRateById_ShouldReturnRateWithMachingId_WhenSuccess() {

    given(findRateByIdQueryHandler.execute(FindRateByIdQuery.builder().rateId("1").build()))
        .willReturn(Mono.just(DomainObjectMother.rate()));

    testClient.get().uri("/v1/rates/1")
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(APPLICATION_JSON)
        .expectBody(Rate.class)
        .consumeWith(response -> {
          Rate responseBody = response.getResponseBody();
          assertThat(responseBody).isNotNull();
          assertThat(responseBody.getId()).isEqualTo(1);
          assertThat(responseBody.getAmount().getValue()).isEqualTo(1704);
          assertThat(responseBody.getBrandId()).isEqualTo(2);
          assertThat(responseBody.getProductId()).isEqualTo(3);
        });
  }

  @Test
  void updateById_ShouldReturnRateAndUpdateItsAmmount_WhenSuccess() {

    Rate rateResponse = DomainObjectMother.rate();
    rateResponse.getAmount().setValue(1111);

    given(updateRateAmountByIdCommandHandler.executeAndReturn(UpdateRateAmountCommand.builder().id("1").price(1111).build()))
        .willReturn(Mono.just(rateResponse));

    testClient.patch().uri("/v1/rates/1")
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(APPLICATION_JSON)
        .expectBody(Rate.class)
        .consumeWith(response -> {
          Rate responseBody = response.getResponseBody();
          assertThat(responseBody).isNotNull();
          assertThat(responseBody.getId()).isEqualTo(rateResponse.getId());
          assertThat(responseBody.getAmount().getValue()).isEqualTo(rateResponse.getAmount().getValue());
          assertThat(responseBody.getBrandId()).isEqualTo(rateResponse.getBrandId());
          assertThat(responseBody.getProductId()).isEqualTo(rateResponse.getProductId());
        });
  }
}
