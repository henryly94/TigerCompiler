package dfa;

import java.util.HashMap;
import java.util.Map;

public class State {
  private String text;
  private final boolean isAcceptState;
  private Map<Character, Integer> destinations;

  private int defaultDestination;
  private int otherPunctuationDestination;
  private int space;
  private int num;

  public State(String text, boolean isAcceptState) {
    this.text = text;
    this.isAcceptState = isAcceptState;
    destinations = new HashMap<Character, Integer>();

    this.defaultDestination = Integer.MAX_VALUE;
    otherPunctuationDestination = Integer.MAX_VALUE;
    space = Integer.MAX_VALUE;
    num = Integer.MAX_VALUE;
  }

  public boolean isAcceptState() {
    return isAcceptState;
  }

  public String getText() {
    return text;
  }

  public int getDestination(char ch) {
    if (destinations.containsKey(ch)) return destinations.get(ch);
    else if (isOtherPunctuation(ch)) return otherPunctuationDestination;
    else if (isSpace(ch)) return space;
    else if (isNum(ch)) return num;
    else return defaultDestination;
  }

  public void addDestination(String tag, int destination) {
    if (tag.equals("DEFAULT")) setDefaultDestination(destination);
    else if (tag.equals("~`!@#$%?")) otherPunctuationDestination = destination;
    else if (tag.equals("SPACE")) space = destination;
    else if (tag.equals("0-9")) num = destination;
    else if (tag.equals("comma")) destinations.put(',', destination);
    else destinations.put(tag.charAt(0), destination);
  }

  private void setDefaultDestination(int destination) {
    defaultDestination = destination;
    if (otherPunctuationDestination == Integer.MAX_VALUE) otherPunctuationDestination = destination;
    if (space == Integer.MAX_VALUE) space = destination;
    if (num == Integer.MAX_VALUE) num = destination;
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
}
