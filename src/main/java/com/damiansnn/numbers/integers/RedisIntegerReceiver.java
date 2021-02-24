package com.damiansnn.numbers.integers;

import static io.vavr.API.Left;
import static io.vavr.API.Right;

import com.damiansnn.commons.AppError;
import com.damiansnn.commons.ConnectionError;
import com.damiansnn.numbers.NumberReceiver;
import io.lettuce.core.api.StatefulRedisConnection;
import io.micronaut.context.annotation.Value;
import io.vavr.control.Either;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
final class RedisIntegerReceiver implements NumberReceiver<Integer> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedisIntegerReceiver.class);

  private final StatefulRedisConnection<String, String> connection;
  private final String numberRedisKey;

  public RedisIntegerReceiver(
      StatefulRedisConnection<String, String> connection,
      @Value("${redis.number-key}") String numberRedisKey) {
    this.connection = connection;
    this.numberRedisKey = numberRedisKey;
  }

  @Override
  public Either<AppError, Integer> receiveNumber() {
    try {
      String rawRandomNumber = connection.sync().get(numberRedisKey);
      if (rawRandomNumber != null) {
        int randomNumber = Integer.parseInt(rawRandomNumber);
        LOGGER.info(
            "Number was retrieved from redis. Key: {}. Value: {}", numberRedisKey, randomNumber);
        return Right(randomNumber);
      } else {
        String message = "Number was not found in redis. Key: " + numberRedisKey;
        LOGGER.warn(message);
        return Left(new ConnectionError(message));
      }
    } catch (RuntimeException exc) {
      LOGGER.warn(
          "There was an exception while retrieving number from redis. Key: {}. "
              + "Exception: {}. Message: {}",
          numberRedisKey,
          exc.getClass().getName(),
          exc.getMessage());
      String errorMessage =
          exc.getMessage() != null ? exc.getMessage() : exc.getClass().getSimpleName();
      return Left(new ConnectionError(errorMessage));
    }
  }
}
