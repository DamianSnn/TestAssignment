package com.damiansnn.numbers;

import com.damiansnn.commons.AppError;
import io.vavr.control.Either;

@FunctionalInterface
public interface NumberReceiver<T> {
  Either<AppError, T> receiveNumber();
}
