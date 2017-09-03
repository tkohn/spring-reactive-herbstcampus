package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model;

import java.util.*;


public enum Symbol {
  Rock,
  Paper,
  Scissors,
  Lizard,
  Spock;

  private static final List<Symbol> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();
  private static final Map<Symbol, List<Symbol>> losersMap = Collections.unmodifiableMap(initMap());

  public static Symbol random() {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }

  private static Map<Symbol, List<Symbol>> initMap() {
    final HashMap<Symbol, List<Symbol>> map = new HashMap<>();
    map.put(Rock, Arrays.asList(Spock, Paper));
    map.put(Paper, Arrays.asList(Scissors, Lizard));
    map.put(Scissors, Arrays.asList(Spock, Rock));
    map.put(Lizard, Arrays.asList(Scissors, Rock));
    map.put(Spock, Arrays.asList(Lizard, Paper));
    return map;
  }

  public static boolean firstWins(final Symbol first, final Symbol second) {
    return losersMap.get(second).contains(first);
  }


}
