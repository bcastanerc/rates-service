package io.virtualcave.rates.api.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.virtualcave.api.rest.dtos.v1.RateRequestDto;
import io.virtualcave.rates.api.rest.mappers.RateDtoMapperImpl;
import io.virtualcave.rates.api.rest.mappers.RateRequestDtoMapper;
import io.virtualcave.rates.application.commands.AddRateCommandHandler;
import io.virtualcave.rates.domain.DomainObjectMother;
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
@ContextConfiguration(classes = {RateApiController.class})
public class RateApiController {

  @MockBean
  private AddRateCommandHandler addRateCommandHandler;

  @SpyBean
  private RateDtoMapperImpl rateDtoMapper;

  @SpyBean
  private RateRequestDtoMapper rateRequestDtoMapper;

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
    .expectBody(RateRequestDto.class)
    .consumeWith(response -> {
      RateRequestDto responseBody = response.getResponseBody();
      assertThat(responseBody).isNotNull();
      assertThat(responseBody.getTotalAmount()).isEqualTo(1704);
      assertThat(responseBody.getBrandId()).isEqualTo("2");
      assertThat(responseBody.getProductId()).isEqualTo("3");
    });
  }
}
