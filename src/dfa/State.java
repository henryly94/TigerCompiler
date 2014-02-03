package dfa;

import java.util.HashMap;
import java.util.Map;

public class State {
  private final String text;
  private final boolean isAcceptState;
  private final Map<Character, Integer> destinations;

  private int defaultDestination;
  private int otherPunctuationDestination;
  private int spaceDestination;
  private int numDestination;

  public State(String text, boolean isAcceptState) {
    this.text = text;
    this.isAcceptState = isAcceptState;
    destinations = new HashMap<Character, Integer>();

    this.defaultDestination = Integer.MAX_VALUE;
    otherPunctuationDestination = Integer.MAX_VALUE;
    spaceDestination = Integer.MAX_VALUE;
    numDestination = Integer.MAX_VALUE;
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
    else if (isSpace(ch)) return spaceDestination;
    else if (isNum(ch)) return numDestination;
    else return defaultDestination;
  }

  public void addDestination(String tag, int destination) {
    if (tag.equals("DEFAULT")) setDefaultDestination(destination);
    else if (tag.equals("~`!@#$%?")) otherPunctuationDestination = destination;
    else if (tag.equals("SPACE")) spaceDestination = destination;
    else if (tag.equals("0-9")) numDestination = destination;
    else if (tag.equals("comma")) destinations.put(',', destination);
    else destinations.put(tag.charAt(0), destination);
  }

  private void setDefaultDestination(int destination) {
    defaultDestination = destination;
    if (otherPunctuationDestination == Integer.MAX_VALUE) otherPunctuationDestination = destination;
    if (spaceDestination == Integer.MAX_VALUE) spaceDestination = destination;
    if (numDestination == Integer.MAX_VALUE) numDestination = destination;
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
