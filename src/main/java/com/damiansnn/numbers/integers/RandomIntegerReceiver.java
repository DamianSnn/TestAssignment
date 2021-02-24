package com.damiansnn.numbers.integers;

import static io.vavr.API.Right;

import com.damiansnn.commons.AppError;
import com.damiansnn.numbers.NumberReceiver;
import io.micronaut.context.annotation.Value;
import io.vavr.control.Either;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
final class RandomIntegerReceiver implements NumberReceiver<Integer> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RandomIntegerReceiver.class);

  private final int min;
  private final int max;

  public RandomIntegerReceiver(
      @Value("${random.integer.min}") int min, @Value("${random.integer.max}") int max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public Either<AppError, Integer> receiveNumber() {
    int randomNumber = getRandomNumber();
    LOGGER.info("Random number was generated. Value: " + randomNumber);
    return Right(randomNumber);
  }

  private int getRandomNumber() {
    return min + (int) (Math.random() * ((max - min) + 1));
  }
}
