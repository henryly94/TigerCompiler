import dfa.State;
import grammar.GrammarBuilder;
import org.junit.Before;
import org.junit.Test;
import parser.GrammarState;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

public class GrammarBuilderTests {

  private GrammarBuilder builder;
  private List<State> exp;

  @Before
  public void setUp() {
    builder = new GrammarBuilder();
  }

  @Test
  public void simpleGrammar() {
    List<State> states = builder.buildFrom("./assets/simplegrammar");
    exp = new ArrayList<State>();
    GrammarState state = new GrammarState("<tag>", -2);
    state.addDestination("word1", 1);
    exp.add(state);
    state = new GrammarState("<tag>", -2);
    state.addDestination("word2", 2);
    exp.add(state);
    state = new GrammarState("<tag>", -2);
    state.addDestination("word3", 3);
    exp.add(state);
    state = new GrammarState("<tag>", -1);
    exp.add(state);
    assertEquals(exp, states);
  }

  @Test
  public void compositeGrammar() {
    List<State> states = builder.buildFrom("./assets/compositegrammar");
    exp = new ArrayList<State>();
    GrammarState state = new GrammarState("<tag1>", -2);
    state.addDestination("word1", 1);
    exp.add(state);
    state = new GrammarState("<tag1>", -2);
    state.addDestination("word2", 2);
    exp.add(state);
    state = new GrammarState("<tag1>", -2);
    state.addDestination("word3", 3);
    exp.add(state);
    state = new GrammarState("<tag1>", -2);
    state.addDestination("<tag2>", 4);
    exp.add(state);
    state = new GrammarState("<tag1>", -1);
    exp.add(state);
    state = new GrammarState("<tag2>", -2);
    state.addDestination("word4", 5);
    exp.add(state);
    state = new GrammarState("<tag2>", -1);
    exp.add(state);
    assertEquals(exp, states);
  }
}
