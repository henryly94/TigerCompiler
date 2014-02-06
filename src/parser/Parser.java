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
    int index = 0;
    while (index < tokens.size()) {
      index = handleToken(index);
      if (index >= tokens.size())
        return;
      System.out.println(dfa.getState() + " " + getType(index) + " " + index);
      if (dfa.isInErrorState()) return;
    }
  }

  private int handleToken(int index) {
    int prevState = dfa.getState();
    dfa.changeState(getType(index));
    int newState = dfa.getState();
    if (dfa.isInReturnState()) handleReturn();
    else if (jumpOccurred(prevState, newState)) handleJump(prevState);
    else index++;
    return index;
  }

  private void handleJump(int prevState) {
    prevStates.push(prevState);
  }

  private void handleReturn() {
    dfa.setState(prevStates.pop() + 1);
  }

  private boolean jumpOccurred(int prevState, int newState) {
    return differentStateNames(prevState, newState) || prevState > newState;
  }

  private boolean differentStateNames(int prevState, int newState) {
    return !(dfa.getType(prevState).equals(dfa.getType(newState)));
  }

  private String getType(int i) {
    return tokens.get(i).getType();
  }

  public boolean isLegal() {
    return dfa.isInAcceptState();
  }
}
