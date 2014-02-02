package dfabuilder;

import dfa.Dfa;
import dfa.State;

import java.util.ArrayList;
import java.util.List;

public class DfaBuilder {

  private final SvReader reader;
  private String[] header;

  public DfaBuilder() {
    reader = new SvReader(',');
  }

  public Dfa buildFrom(String filename) {
    reader.read(filename);
    header = reader.getHeader();
    List<State> states = new ArrayList<State>();
    while (reader.hasLine())
      states.add(getNextState());
    return new Dfa(states);
  }

  private State getNextState() {
    String[] line = reader.getLine();
    State state = new State(line[1], line[line.length-1].equals("yes"));
    for (int i = 2; i < line.length-1; i++)
      if (!line[i].equals(""))
        state.addDestination(header[i], Integer.parseInt(line[i]));
    return state;
  }
}
