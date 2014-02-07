package dfabuilder;

import dfa.Dfa;
import dfa.State;

import java.util.ArrayList;
import java.util.List;

public abstract class DfaBuilder {
  private final SvReader reader;
  private String[] header;

  protected DfaBuilder() {
    reader = new SvReader(',');
  }

  public Dfa buildFrom(String filename) {
    reader.read(filename);
    header = reader.getHeader();
    List<State> states = new ArrayList<State>();
    while (reader.hasLine())
      states.add(getNextState());
    return buildDfa(states);
  }

  protected abstract State getNextState();

  protected String getHeaderEntry(int i) {
    return header[i];
  }

  protected String[] getNextLine() {
    return reader.getLine();
  }

  protected abstract Dfa buildDfa(List<State> states);
}
