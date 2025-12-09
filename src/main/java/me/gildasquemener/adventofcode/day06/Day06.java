package me.gildasquemener.adventofcode.day06;

public class Day06 {
  public double averageWeight(int weights[], int length) {
    if (length == 0) {
      return 0.0;
    }
    double s = 0;
    for (int i = 0; i < length; i++) {
      s += weights[i];
    }
    return s / length;
  }
}
