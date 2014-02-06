package parser;

import dfa.Dfa;
import dfa.State;

import java.util.List;

public class GrammarDfa extends Dfa {
  private String value;

  public GrammarDfa(List<State> states) {
    super(states);
  }

  @Override
  public String getStateValue() {
    return value.substring(0, value.length()-1);
  }

  @Override
  public boolean isInErrorState() {
    return getState() == -2;
  }

  @Override
  public boolean isInAcceptState() {
    return getState() == 5;
  }

  public boolean isInReturnState() {
    return getState() == -1;
  }

  @Override
  protected void adjustValue(String input) {
    value += input + " ";
  }

  @Override
  protected void resetValue() {
    value = "";
  }

  public void setState(int state) {
    super.setState(state);
  }

  public String getType(int state) {
    return states.get(state).getText();
  }
}
