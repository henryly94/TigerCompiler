package parser;

import org.junit.Before;
import org.junit.Test;
import scanner.TokenTuple;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

public class GrammarDfaTests {

  private Parser p;
  private List<TokenTuple> tokens;

  @Before
  public void setUp() {
    p = new Parser((GrammarDfa) new GrammarDfaBuilder().buildFrom("./src/parser/GrammarDFA.csv"));
    tokens = new ArrayList<TokenTuple>();
  }

  private void add(String type) {
    tokens.add(new TokenTuple(type, ""));
  }

  @Test
  public void legalInput() {
    add("LET");
    add("IN");
    add("END");
    p.parse(tokens);
    assertTrue(p.isLegal());
  }

  @Test
  public void illegalInput() {
    add("END");
    p.parse(tokens);
    assertFalse(p.isLegal());
  }
}
