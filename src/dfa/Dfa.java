package dfa;

import java.util.HashMap;
import java.util.Map;

public class Dfa {
  private Map<Integer, State> states;
  private int currState;

  public Dfa() {
    states = new HashMap<Integer, State>();
    currState = 0;
  }

  public void addState(int id, State row) {
    states.put(id, row);
  }

  public void changeState(char c) {
    currState = states.get(currState).getDestination(c);
  }

  public String getToken() {
    return states.get(currState).getText();
  }

  public boolean isInAcceptState() {
    return states.get(currState).isAcceptState();
  }

  public void reset() {
    currState = 0;
  }
}
