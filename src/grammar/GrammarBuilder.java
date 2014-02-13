package grammar;

import dfa.State;
import dfabuilder.FileScraper;
import dfabuilder.StringSplitter;
import parser.GrammarState;

import java.util.ArrayList;
import java.util.List;

public class GrammarBuilder {
  public List<State> buildFrom(String filename) {
    FileScraper fs = new FileScraper();
    String[] lines = fs.read(filename);
    StringSplitter ss = new StringSplitter(' ');

    List<State> states = new ArrayList<State>();
    GrammarState state;
    String[] tokens;
    int gotoState = 0;
    for (String line : lines) {
      tokens = ss.split(line);
      String name = tokens[0].substring(0, tokens[0].length() - 1);
      for (int i = 1; i < tokens.length; i++) {
        state = new GrammarState(name, -2);
        state.addDestination(tokens[i], ++gotoState);
        states.add(state);
      }
      state = new GrammarState(name, -1);
      states.add(state);
    }

    return states;
  }
}
