package dfa;

import java.util.List;

public abstract class Dfa {
  private final List<State> states;
  private int currState;

  public Dfa(List<State> states) {
    this.states = states;
    reset();
  }

  /**
   * It is the responsibility of the user to retrieve tokens and token
   * types once an acceptance state is reached. The user is also
   * responsible for resetting the TokenDfa to allow more tokens to be
   * generated.
   */
  public boolean isInAcceptState() {
    return states.get(currState).isAcceptState();
  }

  public String getStateName() {
    return states.get(currState).getText();
  }

  public void reset() {
    currState = 0;
    resetValue();
  }

  /**
   * Changes the current state of the automaton. It is the responsibility
   * of the user to query tokens, token types, and accept states. If the
   * current state becomes -1 it represents a lexical error.
   */
  public void changeState(String input) {
    adjustState(input);
    adjustValue(input);
  }

  private void adjustState(String input) {
    currState = getCurrState().getDestination(input);
  }

  protected State getCurrState() {
    return states.get(currState);
  }

  public int getState() {
    return currState;
  }

  public abstract String getStateValue();
  public abstract boolean isInErrorState();
  protected abstract void adjustValue(String input);
  protected abstract void resetValue();

  protected void setState(int state) {
    this.currState = state;
  }
}
