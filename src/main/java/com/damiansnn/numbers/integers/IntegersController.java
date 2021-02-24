package com.damiansnn.numbers.integers;

import com.damiansnn.numbers.NumbersService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller("/api/numbers/integers")
final class IntegersController {
  private final NumbersService<Long, Integer> numbersService;

  public IntegersController(NumbersService<Long, Integer> numbersService) {
    this.numbersService = numbersService;
  }

  @Get("/sum")
  @Operation(
      summary = "Return sum of numbers.",
      description = "Returns sum of random number and number from redis.")
  @ApiResponse(
      responseCode = "200",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON,
              schema = @Schema(implementation = IntegersSumDTO.class)))
  public HttpResponse<?> getNumbersSum() {
    return numbersService
        .calculateNumbersSum()
        .map(IntegersSumDTO::new)
        .map(sumDTO -> HttpResponse.ok().body(sumDTO))
        .mapLeft(appError -> HttpResponse.serverError().body(appError))
        .fold(x -> x, x -> x);
  }
}
