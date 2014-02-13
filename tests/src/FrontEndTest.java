import frontend.FrontEnd;
import scanner.TokenDfaBuilder;
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
    Scanner scanner = new Scanner((TokenDfa) new TokenDfaBuilder().buildFrom("./assets/TokenDFA.csv"));
    Parser parser = new Parser((GrammarDfa) new GrammarDfaBuilder().buildFrom("./assets/GrammarDFA.csv"));
    frontEnd = new FrontEnd(scanner, parser);
  }

  @Test
  public void simpleFile() {
    assertTrue(frontEnd.compile("./assets/simplefile"));
  }

  @Test
  public void complexFile() {
    assertTrue(frontEnd.compile("./assets/complexfile"));
  }
}
