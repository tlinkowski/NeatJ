/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.tlinkowski.neatj.core.util;

import java.util.Comparator;
import java.util.Optional;

import lombok.experimental.UtilityClass;

import pl.tlinkowski.annotation.basic.NullOr;

/**
 * @author Tomasz Linkowski
 */
@UtilityClass
public final class NeatComparators {

  /**
   * Returns a comparator that considers {@link Optional#empty()} to be less than a non-empty {@link Optional}. When
   * both are empty, they are considered equal. If both are non-empty, the specified {@link Comparator} is used to
   * determine the order. If the specified comparator is {@code null}, then the returned comparator considers all
   * non-empty values to be equal.
   *
   * @see Comparator#nullsFirst(Comparator)
   */
  public static <T> Comparator<Optional<T>> absentFirst(@NullOr Comparator<? super T> comparator) {
    return Comparator.comparing(optional -> optional.orElse(null), Comparator.nullsFirst(comparator));
  }

  /**
   * Returns a comparator that considers {@link Optional#empty()} to be greater than a non-empty {@link Optional}. When
   * both are empty, they are considered equal. If both are non-empty, the specified {@link Comparator} is used to
   * determine the order. If the specified comparator is {@code null}, then the returned comparator considers all
   * non-empty values to be equal.
   *
   * @see Comparator#nullsLast(Comparator)
   */
  public static <T> Comparator<Optional<T>> absentLast(@NullOr Comparator<? super T> comparator) {
    return Comparator.comparing(optional -> optional.orElse(null), Comparator.nullsLast(comparator));
  }

  /**
   * Returns a comparator that considers {@link Optional#empty()} to be less than a non-empty {@link Optional}. When
   * both are empty, they are considered equal. If both are non-empty, their values are compared according to the
   * natural order.
   *
   * @see #absentFirst(Comparator)
   */
  public static <T extends Comparable<T>> Comparator<Optional<T>> absentFirst() {
    return absentFirst(Comparator.naturalOrder());
  }

  /**
   * Returns a comparator that considers {@link Optional#empty()} to be greater than a non-empty {@link Optional}. When
   * both are empty, they are considered equal. If both are non-empty, their values are compared according to the
   * natural order.
   *
   * @see #absentLast(Comparator)
   */
  public static <T extends Comparable<T>> Comparator<Optional<T>> absentLast() {
    return absentLast(Comparator.naturalOrder());
  }
}
