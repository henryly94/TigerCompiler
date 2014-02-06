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
    return false;
  }

  @Override
  protected void adjustState(String input) {

  }

  @Override
  protected void adjustValue(String input) {
    value += input + " ";
  }

  @Override
  protected void resetValue() {
    value = "";
  }
}
