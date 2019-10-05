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

package pl.tlinkowski.neatj.core.util

import spock.lang.Specification

/**
 * @author Tomasz Linkowski
 */
class NeatComparatorsSpec extends Specification {

  def "absentFirst/Last(Comparator)"(int first, int last, Integer left, Integer right) {
    given:
      def firstComparator = NeatComparators.<Integer> absentFirst(null)
      def lastComparator = NeatComparators.<Integer> absentLast(null)
      def o1 = Optional.ofNullable(left)
      def o2 = Optional.ofNullable(right)
    when:
      def actualFirst = firstComparator.compare(o1, o2)
      def actualLast = lastComparator.compare(o1, o2)
    then:
      first == actualFirst
      last == actualLast
    where:
      first | last | left | right
      0     | 0    | null | null
      -1    | +1   | null | 1
      +1    | -1   | 1    | null
      0     | 0    | 1    | 1
      0     | 0    | 1    | 2
      0     | 0    | 2    | 1
  }

  def "absentFirst/Last()"(int first, int last, Integer left, Integer right) {
    given:
      def firstComparator = NeatComparators.<Integer> absentFirst()
      def lastComparator = NeatComparators.<Integer> absentLast()
      def o1 = Optional.ofNullable(left)
      def o2 = Optional.ofNullable(right)
    when:
      def actualFirst = firstComparator.compare(o1, o2)
      def actualLast = lastComparator.compare(o1, o2)
    then:
      first == actualFirst
      last == actualLast
    where:
      first | last | left | right
      0     | 0    | null | null
      -1    | +1   | null | 1
      +1    | -1   | 1    | null
      0     | 0    | 1    | 1
      -1    | -1   | 1    | 2
      +1    | +1   | 2    | 1
  }
}
