package scanner;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import dfabuilder.DfaBuilder;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static junit.framework.Assert.*;

public class ScannerTests {

  private Scanner scanner;
  private List<TokenTuple> tokens;
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    scanner = new Scanner(new DfaBuilder().buildFrom("./src/dfabuilder/DFA.csv"));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void tearDown() {
    System.setErr(null);
  }

  private void checkTokens(String tokenType, String token, int index) {
    assertEquals(new TokenTuple(tokenType, token), tokens.get(index));
  }

  @Test
  public void scanLine() {
    tokens = scanner.scan("var x := 1 + 1");
    checkTokens("VAR", "var", 0);
    checkTokens("ID", "x", 1);
    checkTokens("ASSIGN", ":=", 2);
    checkTokens("INTLIT", "1", 3);
    checkTokens("PLUS", "+", 4);
    checkTokens("INTLIT", "1", 5);
  }

  @Test
  public void scanManyLines() {
    String[] lines = {"var x := 1 + 1", "type intArray = array of int"};
    tokens = scanner.scan(lines);
    checkTokens("VAR", "var", 0);
    checkTokens("ID", "x", 1);
    checkTokens("ASSIGN", ":=", 2);
    checkTokens("INTLIT", "1", 3);
    checkTokens("PLUS", "+", 4);
    checkTokens("INTLIT", "1", 5);
    checkTokens("TYPE", "type", 6);
    checkTokens("ID", "intArray", 7);
    checkTokens("EQ", "=", 8);
    checkTokens("ARRAY", "array", 9);
    checkTokens("OF", "of", 10);
    checkTokens("ID", "int", 11);
  }

  @Test
  public void scanError() {
    scanner.scan("&^");
    assertEquals("Lexical error! Line: 1, Character: 2, Text: ^\n", errContent.toString());
  }

  @Test
  public void scanErrorOnSecondLine() {
    String[] lines = {"var x := 1 + 1", "type intArray =? array of int"};
    scanner.scan(lines);
    assertEquals("Lexical error! Line: 2, Character: 16, Text: ?\n", errContent.toString());
  }
}
