package parser;

import scanner.TokenTuple;

import java.util.List;

public class Parser {
  private GrammarDfa dfa;
  private List<TokenTuple> tokens;
  private int tokenIndex;

  public Parser(GrammarDfa dfa) {
    this.dfa = dfa;
  }

  public void parse(List<TokenTuple> tokens) {
    this.tokens = tokens;
    tokenIndex = 0;
    while (tokenIndex < tokens.size() && !dfa.isInErrorState())
      handleToken();
  }

  private void handleToken() {
    dfa.changeState(getType());
    if (dfa.isInReturnState())
      handleReturn();
    else if (dfa.isInErrorState())
      ;
    else if (dfa.didJumpOccur() || dfa.wasMoveBackwards())
      handleJump();
    else
      tokenIndex++;
  }

  private void handleJump() {
    dfa.pushReturnState();
  }

  private void handleReturn() {
    dfa.returnToPushedState();
  }

  private String getType() {
    return tokens.get(tokenIndex).getType();
  }

  public boolean isLegal() {
    return dfa.isInAcceptState();
  }
}
