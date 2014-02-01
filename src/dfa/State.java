package dfa;

import java.util.HashMap;
import java.util.Map;

public class State {
  private String text;
  private final boolean isAcceptState;
  private Map<Character, Integer> destinations;

  private final int defaultDestination;
  private int otherPuncDestination;
  private int space;
  private int num;

  public State(String text, int defaultDest, boolean isAcceptState) {
    this.text = text;
    this.isAcceptState = isAcceptState;
    destinations = new HashMap<Character, Integer>();

    this.defaultDestination = defaultDest;
    otherPuncDestination = defaultDest;
    space = defaultDest;
    num = defaultDest;
  }

  public void addDestination(char c, int dest) {
    destinations.put(c, dest);
  }

  public boolean isAcceptState() {
    return isAcceptState;
  }

  public int getDestination(char ch) {
    if (destinations.containsKey(ch)) return destinations.get(ch);
    else if (isOtherPuncChar(ch)) return otherPuncDestination;
    else if (isSpaceChar(ch)) return space;
    else if (isNumChar(ch)) return num;
    else return defaultDestination;
  }

  private boolean isOtherPuncChar(char ch) {
    return ch == '~' || ch == '`' || ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '?';
  }

  private boolean isSpaceChar(char ch) {
    return ch == ' ' || ch == '\t' || ch == '\n';
  }

  private boolean isNumChar(char ch) {
    return ch >= '0' && ch <= '9';
  }

  public String getText() {
    return text;
  }

  public void setOtherPuncDestination(int otherPuncDestination) {
    this.otherPuncDestination = otherPuncDestination;
  }

  public void setSpace(int space) {
    this.space = space;
  }

  public void setNum(int num) {
    this.num = num;
  }
}
