package parser;

import dfa.State;

import java.util.HashMap;
import java.util.Map;

public class GrammarState implements State {
  private final Map<String, Integer> destinations;
  private final String name;
  private final int defaultDestination;

  public GrammarState(String text, int defaultDestination) {
    this.name = text;
    this.defaultDestination = defaultDestination;
    destinations = new HashMap<String, Integer>();
  }

  @Override
  public boolean isAcceptState() {
    return defaultDestination == -1;
  }

  public void addDestination(String tag, int destination) {
    destinations.put(tag, destination);
  }

  @Override
  public int getDestination(String input) {
    if (destinations.containsKey(input))
      return destinations.get(input);
    else
      return defaultDestination;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GrammarState))
      return false;
    GrammarState s = (GrammarState)o;
    return destinations.equals(s.destinations) &&
            name.equals(s.name) &&
            defaultDestination == s.defaultDestination;
  }

  @Override
  public String toString() {
    return "Name: " + name +
            ", def dest: " + defaultDestination +
            ", dests: " + destinations;
  }
}
