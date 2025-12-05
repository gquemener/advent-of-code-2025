package me.gildasquemener.adventofcode.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import me.gildasquemener.adventofcode.day04.Day04.Token.Calory;
import me.gildasquemener.adventofcode.day04.Day04.Token.Empty;
import me.gildasquemener.adventofcode.day04.Day04.Token.Name;

public class Day04 {

  public static void main(String[] args) throws IOException {
    HashMap<String, BigDecimal> elves = new HashMap<>();

    try (BufferedReader br =
        new BufferedReader(
            new FileReader("src/main/java/me/gildasquemener/adventofcode/day04/data"))) {
      List<Token> tokens = tokenize(br);
      String currentName = null;
      for (Token token : tokens) {
        switch (token) {
          case Name t -> {
            elves.putIfAbsent(t.name(), BigDecimal.ZERO);
            currentName = t.name();
          }
          case Calory t -> elves.computeIfPresent(currentName, (a, b) -> b.add(t.quantity()));
          default -> {}
        }
      }
    }

    var top3 =
        elves.entrySet().stream()
            .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
            .limit(3)
            .toList();
    var first = top3.get(0);
    var second = top3.get(1);
    var third = top3.get(2);
    System.out.printf(
        "\uD83C\uDF6A Elf of the Day: %s with %s calories!%n", first.getKey(), first.getValue());
    System.out.printf(
        "\uD83E\uDD48 Then comes %s (%s) and %s (%s)%n",
        second.getKey(), second.getValue(), third.getKey(), third.getValue());
    System.out.printf(
        "\uD83C\uDF81 Combined snack power of Top 3: %s calories!%n",
        first.getValue().add(second.getValue().add(third.getValue())));
  }

  private static List<Token> tokenize(final BufferedReader br) throws IOException {
    var tokens = new ArrayList<Token>();
    String line;
    while ((line = br.readLine()) != null) {
      tokens.add(tokenize(line));
    }

    return tokens.stream().toList();
  }

  private static Token tokenize(final String line) {
    Pattern alpha = Pattern.compile("[A-Za-z]+");
    Pattern numeric = Pattern.compile("[0-9]+");
    if (alpha.matcher(line).matches()) {
      return new Name(line);
    }
    if (numeric.matcher(line).matches()) {
      return new Calory(new BigDecimal(line));
    }

    return new Empty();
  }

  public sealed interface Token {
    record Name(String name) implements Token {}

    record Calory(BigDecimal quantity) implements Token {}

    record Empty() implements Token {}
  }
}
