package com.damiansnn.numbers.integers;

import com.damiansnn.numbers.NumbersCombiner;
import java.util.Collection;
import javax.inject.Singleton;

@Singleton
final class IntegersCombiner implements NumbersCombiner<Long, Integer> {

  @Override
  public Long combineNumbers(Collection<Integer> numbers) {
    return numbers.stream().mapToLong(Integer::longValue).sum();
  }
}
