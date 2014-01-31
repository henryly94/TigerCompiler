package scanner;

import dfa.Dfa;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
  private Dfa dfa;
  private List<TokenTuple> tokenTuples;
  private int lineInd;
  private int charInd;

  public Scanner(Dfa dfa) {
    this.dfa = dfa;
  }

  public List<TokenTuple> scan(String[] toScan) {
    List<TokenTuple> allTokens = new ArrayList<TokenTuple>();
    for (lineInd = 0; lineInd < toScan.length; lineInd++)
      allTokens.addAll(scan(toScan[lineInd]));
    return allTokens;
  }

  public List<TokenTuple> scan(String toScan) {
    toScan += " ";
    tokenTuples = new ArrayList<TokenTuple>();
    for (charInd = 0; charInd < toScan.length(); charInd++)
      handleNextChar(toScan);
    return tokenTuples;
  }

  private void handleNextChar(String toScan) {
    char c = toScan.charAt(charInd);
    dfa.changeState(c);
    if (dfa.isInLexicalErrorState()) handleError();
    else if (dfa.isInSpaceState()) dfa.reset();
    else if (dfa.isInAcceptState()) acceptToken();
  }

  private void handleError() {
    System.err.println("Lexical error! " +
            "Line: " + (lineInd + 1) +
            ", Character: " + (charInd + 1) +
            ", Text: " + dfa.getRawText());
    dfa.reset();
  }

  private void acceptToken() {
    addToken();
    dfa.reset();
    charInd--;
  }

  private void addToken() {
    String tokenType = dfa.getTokenType();
    String token = dfa.getToken();
    tokenTuples.add(new TokenTuple(tokenType, token));
  }
}
