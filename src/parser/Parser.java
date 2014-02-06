package parser;

import scanner.TokenTuple;

import java.util.List;
import java.util.Stack;

public class Parser {
  private GrammarDfa dfa;
  private List<TokenTuple> tokens;
  private Stack<Integer> prevStates;

  public Parser(GrammarDfa dfa) {
    this.dfa = dfa;
    prevStates = new Stack<Integer>();
  }

  public void parse(List<TokenTuple> tokens) {
    this.tokens = tokens;
    for (int i = 0; i < this.tokens.size(); i++) {
      i = handleToken(i);
      if (dfa.isInErrorState())  return;
    }
  }

  private int handleToken(int index) {
    int prevState = dfa.getState();
    dfa.changeState(getType(index));
    int newState = dfa.getState();
    if (dfa.isInReturnState()) index = handleReturn(index);
    else if (jumpOccurred(prevState, newState)) index = handleJump(index, prevState);
    return index;
  }

  private int handleJump(int i, int prevState) {
    prevStates.push(prevState);
    return i-1;
  }

  private int handleReturn(int i) {
    dfa.setState(prevStates.pop() + 1);
    return i-1;
  }

  private boolean jumpOccurred(int prevState, int newState) {
    return Math.abs(newState - prevState) > 1;
  }

  private String getType(int i) {
    return tokens.get(i).getType();
  }

  public boolean isLegal() {
    return dfa.isInAcceptState();
  }
}
