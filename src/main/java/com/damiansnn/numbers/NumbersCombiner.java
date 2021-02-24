package com.damiansnn.numbers;

import java.util.Collection;

@FunctionalInterface
public interface NumbersCombiner<V, T> {
  V combineNumbers(Collection<T> numbers);
}
