package dfabuilder;

import dfa.Dfa;
import dfa.State;

import java.util.ArrayList;
import java.util.List;

public class DfaBuilder {

  private final SvReader reader;
  private String[] header;

  public DfaBuilder() {
    reader = new SvReader();
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
    State state = new State(line[1], Integer.parseInt(line[2]), line[line.length-1].equals("yes"));
    for (int i = 3; i < line.length-1; i++)
      if (!line[i].equals(""))
        addDestination(line, state, i);
    return state;
  }

  private void addDestination(String[] line, State state, int i) {
    if (header[i].equals("~`!@#$%?")) state.setOtherPunctuationDestination(Integer.parseInt(line[i]));
    else if (header[i].equals("SPACE")) state.setSpace(Integer.parseInt(line[i]));
    else if (header[i].equals("0-9")) state.setNum(Integer.parseInt(line[i]));
    else if (header[i].equals("COM")) state.addDestination(',', Integer.parseInt(line[i]));
    else state.addDestination(header[i].charAt(0), Integer.parseInt(line[i]));
  }
}
