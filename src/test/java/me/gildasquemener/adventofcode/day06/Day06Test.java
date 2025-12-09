package me.gildasquemener.adventofcode.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day06Test {

  @Test
  void longList() {
    var result = new Day06().averageWeight(new int[] {2, 5, 7, 10}, 4);
    assertEquals(result, 6.00);
  }

  @Test
  void shortList() {
    var result = new Day06().averageWeight(new int[] {2}, 1);
    assertEquals(result, 2.00);
  }

  @Test
  void emptyList() {
    var result = new Day06().averageWeight(new int[] {}, 0);
    assertEquals(result, 0.00);
  }

  @Test
  void realResult() {
    var result = new Day06().averageWeight(new int[] {1, 2}, 2);
    assertEquals(result, 1.50);
  }
}
