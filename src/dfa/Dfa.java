package dfa;

import java.util.HashMap;
import java.util.Map;

public class Dfa {
  private Map<Integer, State> states;
  private int currState;
  private String currToken;

  public Dfa() {
    states = new HashMap<Integer, State>();
    reset();
  }

  public void addState(int id, State row) {
    states.put(id, row);
  }

  public void changeState(char c) {
    currState = states.get(currState).getDestination(c);
    currToken += c;
  }

  public String getTokenType() {
    return states.get(currState).getText();
  }

  public String getToken() {
    return currToken.substring(0, currToken.length()-1);
  }

  public boolean isInAcceptState() {
    return states.get(currState).isAcceptState();
  }

  public boolean isSpace() {
    return currState == 0;
  }

  public void reset() {
    currState = 0;
    currToken = "";
  }
}
