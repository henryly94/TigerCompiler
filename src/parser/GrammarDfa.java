package parser;

import dfa.Dfa;
import dfa.State;
import scanner.TokenTuple;

import java.util.List;
import java.util.Stack;

public class GrammarDfa extends Dfa {
  private final Stack<Integer> prevStates;
  private int prevState;

  public GrammarDfa(List<State> states) {
    super(states);
    prevStates = new Stack<Integer>();
  }

  public boolean notInErrorState() {
    return getState() != -2;
  }

  public boolean isInAcceptState() {
    return getState() == 5;
  }

  public boolean isInReturnState() {
    return getState() == -1;
  }

  @Override
  protected void adjustState(String input) {
    prevState = getState();
    super.adjustState(input);
  }

  @Override
  protected void adjustValue(String input) {
  }

  @Override
  protected void resetValue() {
  }

  public void returnToPushedState() {
    super.setState(prevStates.pop() + 1);
  }

  boolean didJumpOccur() {
    return !(states.get(prevState).getName().equals(getCurrState().getName()))
            || prevState > getState();
  }

  void pushReturnState() {
    prevStates.push(prevState);
  }

  public TokenTuple getExpectedToken() {
    return new TokenTuple(getStateName(), "");
  }
}
