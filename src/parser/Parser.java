package parser;

import scanner.TokenTuple;

import java.util.List;

public class Parser {
  private ParseTree tree;
  private final GrammarDfa dfa;
  private List<TokenTuple> tokens;
  private int tokenIndex;

  public Parser(GrammarDfa dfa) {
    this.dfa = dfa;
  }

  public boolean isLegal() {
    return dfa.isInAcceptState();
  }

  public ParseTree parse(List<TokenTuple> tokens) {
    prepareToParse(tokens);
    while (tokensRemain() && dfa.notInErrorState())
      processNextToken();
    return tree;
  }

  private void prepareToParse(List<TokenTuple> tokens) {
    tree = new ParseTree();
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
      executeReturn();
    else if (dfa.didJumpOccur())
      executeJump();
    else
      moveToNextToken();
  }

  private void executeReturn() {
    tree.moveToParent();
    dfa.returnToPushedState();
  }

  private void executeJump() {
    tree.addBranch(dfa.getExpectedToken());
    tree.moveToLastBranch();
    dfa.pushReturnState();
  }

  private void moveToNextToken() {
    tree.addBranch(tokens.get(tokenIndex));
    tokenIndex++;
  }
}
