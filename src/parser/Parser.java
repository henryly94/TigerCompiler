package parser;

import scanner.TokenTuple;

import java.util.List;

public class Parser {
  private final GrammarDfa dfa;
  private List<TokenTuple> tokens;
  private int tokenIndex;
  private ParseTree tree;

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
      repeatTokenAndLoadState();
    else if (dfa.didJumpOccur())
      repeatTokenAndSaveState();
    else
      moveToNextToken();
  }

  private void repeatTokenAndLoadState() {
    tree.moveUp();
    dfa.returnToPushedState();
  }

  private void repeatTokenAndSaveState() {
    System.out.println(dfa.getStateName());
    tree.addBranch(new TokenTuple(dfa.getStateName(), ""));
    tree.moveDown();
    dfa.pushReturnState();
  }

  private void moveToNextToken() {
    tree.addBranch(tokens.get(tokenIndex));
    tokenIndex++;
  }
}
