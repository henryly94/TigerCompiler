import org.junit.Before;
import org.junit.Test;
import parser.GrammarDfa;
import parser.GrammarDfaBuilder;
import parser.ParseTree;
import parser.Parser;
import scanner.TokenTuple;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

public class ParserTests {

  private Parser p;
  private List<TokenTuple> tokens;

  @Before
  public void setUp() {
    p = new Parser((GrammarDfa) new GrammarDfaBuilder().buildFrom("./assets/GrammarDFA.csv"));
    tokens = new ArrayList<TokenTuple>();
  }

  private void add(String type) {
    tokens.add(new TokenTuple(type, type.toLowerCase()));
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

  @Test
  public void simpleInput() {
    add("LET");
    add("IN");
    add("END");

    ParseTree exp = new ParseTree();
    exp.addBranch(new TokenTuple("LET", "let"));
    exp.addBranch(new TokenTuple("<declaration-segment>", ""));
    exp.moveToLastBranch();
    exp.addBranch(new TokenTuple("<type-declaration-list>", ""));
    exp.addBranch(new TokenTuple("<var-declaration-list>", ""));
    exp.addBranch(new TokenTuple("<funct-declaration-list>", ""));
    exp.moveToParent();
    exp.addBranch(new TokenTuple("IN", "in"));
    exp.addBranch(new TokenTuple("<stat-seq>", ""));
    exp.addBranch(new TokenTuple("END", "end"));

    assertEquals(exp, p.parse(tokens));
  }
}
