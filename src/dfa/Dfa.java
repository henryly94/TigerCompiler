package dfa;

import java.util.List;

public class Dfa {
  private final List<State> states;
  private int currState;
  private String currToken;

  public Dfa(List<State> states) {
    this.states = states;
    reset();
  }

  /**
   * Changes the current state of the automaton. It is the responsibility
   * of the user to query tokens, token types, and accept states. If the
   * current state becomes -1 it represents a lexical error.
   */
  public void changeState(char c) {
    currState = states.get(currState).getDestination(c);
    currToken += c;
  }

  /**
   * It is the responsibility of the user to retrieve tokens and token
   * types once an acceptance state is reached. The user is also
   * responsible for resetting the Dfa to allow more tokens to be
   * generated.
   */
  public boolean isInAcceptState() {
    return states.get(currState).isAcceptState();
  }

  public String getTokenType() {
    return states.get(currState).getText();
  }

  /**
   * Returns the current token with any trailing characters removed.
   */
  public String getToken() {
    return currToken.substring(0, currToken.length()-1);
  }

  public boolean isInSpaceState() {
    return states.get(currState).getText().equals("SPACE");
  }

  public boolean isInLexicalErrorState() {
    return currState == -1;
  }

  public void reset() {
    currState = 0;
    currToken = "";
  }
}
