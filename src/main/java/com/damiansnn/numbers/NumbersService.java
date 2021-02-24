package com.damiansnn.numbers;

import static java.util.stream.Collectors.toUnmodifiableList;

import com.damiansnn.commons.AppError;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.List;
import javax.inject.Singleton;

@Singleton
public final class NumbersService<V, T> {
  private final Collection<NumberReceiver<T>> numberReceivers;
  private final NumbersCombiner<V, T> numbersCombiner;

  public NumbersService(
      Collection<NumberReceiver<T>> numberReceivers, NumbersCombiner<V, T> numbersCombiner) {
    this.numberReceivers = numberReceivers;
    this.numbersCombiner = numbersCombiner;
  }

  public Either<AppError, V> calculateNumbersSum() {
    return Either.sequenceRight(callReceivers())
        .map(Seq::asJava)
        .map(numbersCombiner::combineNumbers);
  }

  private List<Either<AppError, T>> callReceivers() {
    return numberReceivers.stream()
        .map(NumberReceiver::receiveNumber)
        .collect(toUnmodifiableList());
  }
}
