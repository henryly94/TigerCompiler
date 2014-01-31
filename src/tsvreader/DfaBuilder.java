package tsvreader;

import dfa.Dfa;
import dfa.State;

import java.util.ArrayList;
import java.util.List;

public class DfaBuilder {
  private final TsvReader reader;
  private List<State> states;

  public DfaBuilder() {
    reader = new TsvReader();
    states = new ArrayList<State>();
  }

  public Dfa buildFrom(String filename) {
    reader.read(filename);
    while (reader.hasLine()) states.add(buildState());
    return new Dfa(states);
  }

  private State buildState() {
    String[] data = reader.getLine();
    boolean b = data[50].equals("yes");
    return new State(data[1],
            Integer.parseInt(data[2]),
            Integer.parseInt(data[3]),
            Integer.parseInt(data[4]),
            Integer.parseInt(data[5]),
            Integer.parseInt(data[6]),
            Integer.parseInt(data[7]),
            Integer.parseInt(data[8]),
            Integer.parseInt(data[9]),
            Integer.parseInt(data[10]),
            Integer.parseInt(data[11]),
            Integer.parseInt(data[12]),
            Integer.parseInt(data[13]),
            Integer.parseInt(data[14]),
            Integer.parseInt(data[15]),
            Integer.parseInt(data[16]),
            Integer.parseInt(data[17]),
            Integer.parseInt(data[18]),
            Integer.parseInt(data[19]),
            Integer.parseInt(data[20]),
            Integer.parseInt(data[21]),
            Integer.parseInt(data[22]),
            Integer.parseInt(data[23]),
            Integer.parseInt(data[24]),
            Integer.parseInt(data[25]),
            Integer.parseInt(data[26]),
            Integer.parseInt(data[27]),
            Integer.parseInt(data[28]),
            Integer.parseInt(data[29]),
            Integer.parseInt(data[30]),
            Integer.parseInt(data[31]),
            Integer.parseInt(data[32]),
            Integer.parseInt(data[33]),
            Integer.parseInt(data[34]),
            Integer.parseInt(data[35]),
            Integer.parseInt(data[36]),
            Integer.parseInt(data[37]),
            Integer.parseInt(data[38]),
            Integer.parseInt(data[39]),
            Integer.parseInt(data[40]),
            Integer.parseInt(data[41]),
            Integer.parseInt(data[42]),
            Integer.parseInt(data[43]),
            Integer.parseInt(data[44]),
            Integer.parseInt(data[45]),
            Integer.parseInt(data[46]),
            Integer.parseInt(data[47]),
            Integer.parseInt(data[48]),
            Integer.parseInt(data[49]),
            b);
  }
}
