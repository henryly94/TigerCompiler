package scanner;

import dfa.Dfa;

import java.util.ArrayList;
import java.util.List;

class Scanner {
  private final Dfa dfa;
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
    toScan = prepareForScanning(toScan);
    return makeTokenTupleList(toScan);
  }

  private List<TokenTuple> makeTokenTupleList(String toScan) {
    tokenTuples = new ArrayList<TokenTuple>();
    for (charInd = 0; charInd < toScan.length(); charInd++)
      handleNextChar(toScan);
    return tokenTuples;
  }

  private String prepareForScanning(String toScan) {
    return toScan + " ";
  }

  private void handleNextChar(String toScan) {
    dfa.changeState(toScan.charAt(charInd));
    if (dfa.isInLexicalErrorState()) handleError();
    else if (dfa.isInSpaceState()) handleSpace();
    else if (dfa.isInAcceptState()) acceptToken();
  }

  private void handleError() {
    System.err.println("Lexical error! " +
            "Line: " + (lineInd + 1) +
            ", Character: " + (charInd + 1) +
            ", Text: " + dfa.getRawText());
    dfa.reset();
  }

  private void handleSpace() {
    dfa.reset();
  }

  private void acceptToken() {
    addTokenTuple();
    dfa.reset();
    charInd--;
  }

  private void addTokenTuple() {
    String tokenType = dfa.getTokenType();
    String token = dfa.getToken();
    tokenTuples.add(new TokenTuple(tokenType, token));
  }
}
