package dfa;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import tsvreader.DfaBuilder;

import static junit.framework.Assert.*;

public class TigerDfaTests {
  private Dfa dfa;

  @Before
  public void setUp() {
    dfa = new DfaBuilder().buildFrom("./src/tsvreader/DFA.tsv");
  }

  @After
  public void tearDown() {
    dfa.reset();
  }

  private void changeByString(String toSend) {
    for (char c: toSend.toCharArray())
      dfa.changeState(c);
  }

  @Test
  public void testComma() {
    changeByString(", ");
    assertTrue(dfa.isInAcceptState());
    assertEquals("COMMA", dfa.getTokenType());
    assertEquals(",", dfa.getToken());
  }

  @Test
  public void notDoneBeforeOutOfPlaceChar() {
    changeByString(":");
    assertFalse(dfa.isInAcceptState());
  }

  @Test
  public void testFunction() {
    changeByString("function ");
    assertTrue(dfa.isInAcceptState());
    assertEquals("FUNC", dfa.getTokenType());
    assertEquals("function", dfa.getToken());
  }

  @Test
  public void testId() {
    changeByString("functiona ");
    assertTrue(dfa.isInAcceptState());
    assertEquals("ID", dfa.getTokenType());
    assertEquals("functiona", dfa.getToken());
  }

  @Test
  public void testSpace() {
    changeByString(" ");
    assertTrue(dfa.isInSpaceState());
  }
}