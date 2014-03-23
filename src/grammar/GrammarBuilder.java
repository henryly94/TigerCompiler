package grammar;

import dfa.State;
import dfabuilder.FileScraper;
import dfabuilder.StringSplitter;
import parser.GrammarState;

import java.util.ArrayList;
import java.util.List;

public class GrammarBuilder {

  private List<State> states;
  private int currentStateNum;
  private String[] tokens;
  private StringSplitter ss;
  private final FileScraper fs;

  public GrammarBuilder() {
    fs = new FileScraper();
    ss = new StringSplitter(' ');
  }

  public List<State> buildFrom(String filename) {
    String[] lines = initialize(filename);
    for (String line : lines)
      getStatesFromLine(line);
    return states;
  }

  private String[] initialize(String filename) {
    currentStateNum = 0;
    states = new ArrayList<State>();
    return fs.read(filename);
  }

  private void getStatesFromLine(String line) {
    tokens = ss.split(line);
    String stateName = getStateName();
    for (int tokenNum = 1; tokenNum < tokens.length; tokenNum++)
      generateIntermediateState(stateName, tokenNum);
    generateReturnState(stateName);
  }

  private String getStateName() {
    return tokens[0].substring(0, tokens[0].length() - 1);
  }

  private void generateIntermediateState(String name, int tokenNum) {
    GrammarState state = new GrammarState(name, -2);
    state.addDestination(tokens[tokenNum], ++currentStateNum);
    states.add(state);
  }

  private void generateReturnState(String name) {
    GrammarState state = new GrammarState(name, -1);
    states.add(state);
  }
}
