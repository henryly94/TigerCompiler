package parser;

import dfa.Dfa;
import dfa.State;
import dfabuilder.DfaBuilder;

import java.util.List;

public class GrammarDfaBuilder extends DfaBuilder {

  @Override
  protected State getNextState() {
    String[] line = getNextLine();
    GrammarState state = new GrammarState(line[1], Integer.parseInt(line[3]));
    for (int i = 4; i < line.length; i++)
      if (!line[i].equals(""))
        state.addDestination(getHeaderEntry(i), Integer.parseInt(line[i]));
    return state;
  }

  @Override
  protected Dfa buildDfa(List<State> states) {
    return new GrammarDfa(states);
  }
}
