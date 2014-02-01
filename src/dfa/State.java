package dfa;

import java.util.HashMap;
import java.util.Map;

public class State {
  private String text;
  private final boolean isAcceptState;
  private Map<Character, Integer> destinations;

  private final int defaultDestination;
  private int otherPunctuationDestination;
  private int space;
  private int num;

  public State(String text, int defaultDestination, boolean isAcceptState) {
    this.text = text;
    this.isAcceptState = isAcceptState;
    destinations = new HashMap<Character, Integer>();

    this.defaultDestination = defaultDestination;
    otherPunctuationDestination = defaultDestination;
    space = defaultDestination;
    num = defaultDestination;
  }

  public void addDestination(char c, int destination) {
    destinations.put(c, destination);
  }

  public boolean isAcceptState() {
    return isAcceptState;
  }

  public int getDestination(char ch) {
    if (destinations.containsKey(ch)) return destinations.get(ch);
    else if (isOtherPunctuation(ch)) return otherPunctuationDestination;
    else if (isSpace(ch)) return space;
    else if (isNum(ch)) return num;
    else return defaultDestination;
  }

  private boolean isOtherPunctuation(char ch) {
    return ch == '~' || ch == '`' || ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '?';
  }

  private boolean isSpace(char ch) {
    return ch == ' ' || ch == '\t' || ch == '\n';
  }

  private boolean isNum(char ch) {
    return ch >= '0' && ch <= '9';
  }

  public String getText() {
    return text;
  }

  public void setOtherPunctuationDestination(int otherPunctuationDestination) {
    this.otherPunctuationDestination = otherPunctuationDestination;
  }

  public void setSpace(int space) {
    this.space = space;
  }

  public void setNum(int num) {
    this.num = num;
  }
}
