package parser;

import scanner.TokenTuple;

import java.util.List;

public class Parser {
  private final GrammarDfa dfa;
  private List<TokenTuple> tokens;
  private int tokenIndex;

  public Parser(GrammarDfa dfa) {
    this.dfa = dfa;
  }

  public boolean isLegal() {
    return dfa.isInAcceptState();
  }

  public void parse(List<TokenTuple> tokens) {
    prepareToParse(tokens);
    while (tokensRemain() && dfa.notInErrorState())
      processNextToken();
  }

  private void prepareToParse(List<TokenTuple> tokens) {
    this.tokens = tokens;
    tokenIndex = 0;
  }

  private boolean tokensRemain() {
    return tokenIndex < tokens.size();
  }

  private void processNextToken() {
    dfa.changeState(getNextToken());
    if (dfa.notInErrorState())
      determineNextToken();
  }

  private String getNextToken() {
    return tokens.get(tokenIndex).getType();
  }

  private void determineNextToken() {
    if (dfa.isInReturnState())
      repeatTokenAndLoadState();
    else if (dfa.didJumpOccur())
      repeatTokenAndSaveState();
    else
      moveToNextToken();
  }

  private void repeatTokenAndLoadState() {
    dfa.returnToPushedState();
  }

  private void repeatTokenAndSaveState() {
    dfa.pushReturnState();
  }

  private void moveToNextToken() {
    tokenIndex++;
  }
}
