package frontend;

import dfabuilder.TokenDfaBuilder;
import org.junit.Before;
import org.junit.Test;
import parser.GrammarDfa;
import parser.GrammarDfaBuilder;
import parser.Parser;
import scanner.Scanner;
import scanner.TokenDfa;

import static junit.framework.Assert.*;

public class FrontEndTest {

  private FrontEnd frontEnd;

  @Before
  public void setUp() {
    Scanner scanner = new Scanner((TokenDfa) new TokenDfaBuilder().buildFrom("./src/dfabuilder/TokenDFA.csv"));
    Parser parser = new Parser((GrammarDfa) new GrammarDfaBuilder().buildFrom("./src/parser/GrammarDFA.csv"));
    frontEnd = new FrontEnd(scanner, parser);
  }

  @Test
  public void simpleFile() {
    assertTrue(frontEnd.compile("./src/frontend/simplefile"));
  }

  @Test
  public void complexFile() {
    assertTrue(frontEnd.compile("./src/frontend/complexfile"));
  }
}
