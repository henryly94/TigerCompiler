package dfa;

import org.junit.After;
import org.junit.Test;

import static dfa.Tiger.tigerDfa;
import static junit.framework.Assert.*;

public class TigerDfaTests {

  @After
  public void tearDown() {
    tigerDfa.reset();
  }

  private void changeByString(String toSend) {
    for (char c: toSend.toCharArray())
      tigerDfa.changeState(c);
  }

  @Test
  public void testComma() {
    changeByString(", ");
    assertTrue(tigerDfa.isInAcceptState());
    assertEquals("COMMA", tigerDfa.getToken());
  }

  @Test
  public void notDoneBeforeOutOfPlaceChar() {
    changeByString(":");
    assertFalse(tigerDfa.isInAcceptState());
  }

  @Test
  public void testFunction() {
    changeByString("function ");
    assertTrue(tigerDfa.isInAcceptState());
    assertEquals("FUNC", tigerDfa.getToken());
  }

  @Test
  public void testId() {
    changeByString("functiona ");
    assertTrue(tigerDfa.isInAcceptState());
    assertEquals("ID", tigerDfa.getToken());
  }
}
