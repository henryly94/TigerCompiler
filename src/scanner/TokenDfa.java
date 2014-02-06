package scanner;

import dfa.Dfa;
import dfa.State;

import java.util.List;

public class TokenDfa extends Dfa {
  private String currValue;

  public TokenDfa(List<State> states) {
    super(states);
  }

  protected void adjustValue(String input) {
    currValue += input.charAt(0);
  }

  /**
   * Returns the current token with any trailing characters removed.
   */
  public String getStateValue() {
    return currValue.substring(0, currValue.length()-1);
  }

  public boolean isInSpaceState() {
    return getStateName().equals("SPACE");
  }

  public boolean isInErrorState() {
    return currState == -1;
  }

  @Override
  protected void adjustState(String input) {
    currState = states.get(currState).getDestination(input);
  }

  public void resetValue() {
    currValue = "";
  }
}
