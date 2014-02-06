package scanner;

import dfabuilder.TokenDfaBuilder;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.*;

public class ScannerTests {

  private Scanner scanner;
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    scanner = new Scanner((TokenDfa) new TokenDfaBuilder().buildFrom("./src/dfabuilder/TokenDFA.csv"));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void tearDown() {
    System.setErr(null);
  }

  private void checkTokens(String tokenType, String token) {
    assertEquals(new TokenTuple(tokenType, token), scanner.getNextToken());
  }

  @Test
  public void scanLine() {
    scanner.scan("var x := 1 + 1");
    checkTokens("VAR", "var");
    checkTokens("ID", "x");
    checkTokens("ASSIGN", ":=");
    checkTokens("INTLIT", "1");
    checkTokens("PLUS", "+");
    checkTokens("INTLIT", "1");
    assertFalse(scanner.hasMoreTokens());
  }

  @Test
  public void scanManyLines() {
    String[] lines = {"var x := 1 + 1", "type intArray = array of int"};
    scanner.scan(lines);
    checkTokens("VAR", "var");
    checkTokens("ID", "x");
    checkTokens("ASSIGN", ":=");
    checkTokens("INTLIT", "1");
    checkTokens("PLUS", "+");
    checkTokens("INTLIT", "1");
    checkTokens("TYPE", "type");
    checkTokens("ID", "intArray");
    checkTokens("EQ", "=");
    checkTokens("ARRAY", "array");
    checkTokens("OF", "of");
    checkTokens("INT", "int");
    assertFalse(scanner.hasMoreTokens());
  }

  @Test
  public void hasNoTokens() {
    scanner.scan(" ");
    assertFalse(scanner.hasMoreTokens());
  }

  @Test (expected = LexicalException.class)
  public void scanError() {
    scanner.scan("&^");
    while (scanner.hasMoreTokens())
      scanner.getNextToken();
  }

  @Test (expected = LexicalException.class)
  public void scanErrorOnSecondLine() {
    String[] lines = {"var x := 1 + 1", "type intArray =? array of int"};
    scanner.scan(lines);
    while (scanner.hasMoreTokens())
      scanner.getNextToken();
  }
}
