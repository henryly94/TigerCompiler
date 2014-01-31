package scanner;

import dfa.Tiger;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

public class ScannerTests {

  @Test
  public void scanLine() {
    Scanner scanner = new Scanner(Tiger.tigerDfa);
    List<TokenTuple> tokens = scanner.scan("var x := 1 + 1");
    assertEquals(new TokenTuple("VAR", "var"), tokens.get(0));
    assertEquals(new TokenTuple("ID", "x"), tokens.get(1));
    assertEquals(new TokenTuple("ASSIGN", ":="), tokens.get(2));
    assertEquals(new TokenTuple("INTLIT", "1"), tokens.get(3));
    assertEquals(new TokenTuple("PLUS", "+"), tokens.get(4));
    assertEquals(new TokenTuple("INTLIT", "1"), tokens.get(5));
  }
}
