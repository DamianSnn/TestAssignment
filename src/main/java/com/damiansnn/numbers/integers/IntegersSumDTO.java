package com.damiansnn.numbers.integers;

import java.util.Objects;

final class IntegersSumDTO {
  private final long sum;

  public IntegersSumDTO(long sum) {
    this.sum = sum;
  }

  public long getSum() {
    return sum;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IntegersSumDTO that = (IntegersSumDTO) o;
    return sum == that.sum;
  }

  @Override
  public int hashCode() {
    return Objects.hash(sum);
  }

  @Override
  public String toString() {
    return "NumbersSumDTO{" + "sum=" + sum + '}';
  }
}
