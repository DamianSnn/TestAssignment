package com.damiansnn.numbers;

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.damiansnn.commons.AppError;
import com.damiansnn.commons.ConnectionError;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class NumbersServiceTest {

  @Test
  void testCalculateIntegerNumbersSum() {
    NumbersService<Long, Integer> numbersService =
        new NumbersService<>(
            List.of(() -> Right(3), () -> Right(-4), () -> Right(3)),
            numbers -> numbers.stream().mapToLong(Integer::longValue).sum());
    assertEquals(Right(2L), numbersService.calculateNumbersSum());
  }

  @Test
  void testCalculateIntegerNumbersSize() {
    NumbersService<Integer, Integer> numbersService =
        new NumbersService<>(
            List.of(() -> Right(5), () -> Right(5), () -> Right(5)), Collection::size);
    assertEquals(Right(3), numbersService.calculateNumbersSum());
  }

  @Test
  void testCalculateIntegerWithoutReceivers() {
    NumbersService<Integer, Integer> numbersService =
        new NumbersService<>(List.of(), Collection::size);
    assertEquals(Right(0), numbersService.calculateNumbersSum());
  }

  @Test
  void testCalculateIntegerNumbersWhenError() {
    AppError error = new ConnectionError("Error!");
    NumbersService<Long, Integer> numbersService =
        new NumbersService<>(
            List.of(() -> Right(3), () -> Left(error)), numbers -> (long) numbers.size());
    assertEquals(Left(error), numbersService.calculateNumbersSum());
  }

  @Test
  void testCalculateDoubleNumbersSum() {
    double accuracy = 0.001;
    NumbersService<Double, Double> numbersService =
        new NumbersService<>(
            List.of(() -> Right(1.75), () -> Right(2.25)),
            numbers -> numbers.stream().reduce(0.0, Double::sum));
    assertEquals(4.0, numbersService.calculateNumbersSum().get(), accuracy);
  }
}
