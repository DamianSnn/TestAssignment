package com.damiansnn.numbers.integers;

import static io.vavr.API.Right;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.damiansnn.commons.AppError;
import com.damiansnn.numbers.NumberReceiver;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.vavr.control.Either;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.junit.jupiter.api.Test;

@MicronautTest
@Property(name = "random.integer.min", value = "1000")
@Property(name = "random.integer.max", value = "1000")
class IntegersControllerTest {

  @Inject IntegersController integersController;

  @Test
  void testIntegersController() {
    assertEquals(new IntegersSumDTO(1001L), integersController.getNumbersSum().body());
  }

  @Replaces(RedisIntegerReceiver.class)
  @Singleton
  static class NumberReceiverMock implements NumberReceiver<Integer> {

    @Override
    public Either<AppError, Integer> receiveNumber() {
      return Right(1);
    }
  }
}
