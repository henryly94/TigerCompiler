package dfa;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import dfabuilder.DfaBuilder;
import scanner.TokenDfa;

import static junit.framework.Assert.*;

public class DfaTests {
  private TokenDfa dfa;

  @Before
  public void setUp() {
    dfa = new DfaBuilder().buildFrom("./src/dfabuilder/DFA.csv");
  }

  @After
  public void tearDown() {
    dfa.reset();
  }

  private void changeByString(String toSend) {
    for (char c: toSend.toCharArray())
      dfa.changeState("" + c);
  }

  @Test
  public void testComma() {
    changeByString(", ");
    assertTrue(dfa.isInAcceptState());
    assertEquals("COMMA", dfa.getStateName());
    assertEquals(",", dfa.getStateValue());
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
    assertEquals("FUNC", dfa.getStateName());
    assertEquals("function", dfa.getStateValue());
  }

  @Test
  public void testId() {
    changeByString("functiona ");
    assertTrue(dfa.isInAcceptState());
    assertEquals("ID", dfa.getStateName());
    assertEquals("functiona", dfa.getStateValue());
  }

  @Test
  public void testSpace() {
    changeByString(" ");
    assertTrue(dfa.isInSpaceState());
  }
}