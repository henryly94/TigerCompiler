package scanner;

import dfa.Dfa;

class Scanner {
  private final Dfa dfa;
  private int lineInd;
  private int charInd;
  private String[] toScan;
  private TokenTuple tokenTuple;

  public Scanner(Dfa dfa) {
    this.dfa = dfa;
  }

  public void scan(String[] toScan) {
    this.toScan = toScan;
    lineInd = 0;
    charInd = 0;
    for (int i = 0; i < toScan.length; i++)
      this.toScan[i] += " ";
  }

  public void scan(String toScan) {
    scan(new String[]{toScan});
  }

  public boolean hasMoreTokens() {
    while (isInBounds() && dfa.isInSpaceState())
      moveThroughSpace();
    return isInBounds();
  }

  private boolean isInBounds() {
    return lineInd < toScan.length;
  }

  private void moveThroughSpace() {
    handleSpace();
    sendCurrChar();
    if (dfa.isInLexicalErrorState())
      handleError();
    if (charInd >= toScan[lineInd].length())
      goToNextLine();
  }

  /**
   * Finds the next token in the scanned String and returns it. If
   * a lexical error is found, a LexicalException is thrown.
   */
  public TokenTuple getNextToken() {
    tokenTuple = null;
    while (tokenTuple == null)
      tryNextChar();
    return tokenTuple;
  }

  private void tryNextChar() {
    if (charInd >= toScan[lineInd].length())
      tryNextLine();
    else
      handleNextChar();
  }

  private void tryNextLine() {
    goToNextLine();
    tryNextChar();
  }

  private void handleNextChar() {
    sendCurrChar();
    if (dfa.isInLexicalErrorState()) handleError();
    else if (dfa.isInSpaceState()) handleSpace();
    else if (dfa.isInAcceptState()) acceptToken();
  }

  private void goToNextLine() {
    charInd = 0;
    lineInd++;
  }

  private void sendCurrChar() {
    dfa.changeState(toScan[lineInd].charAt(charInd));
    charInd++;
  }

  private void handleSpace() {
    dfa.reset();
  }

  private void handleError() {
    dfa.reset();
    throw new LexicalException(lineInd + 1, charInd + 1);
  }

  private void acceptToken() {
    setTokenTuple();
    dfa.reset();
    charInd--;
  }

  private void setTokenTuple() {
    String tokenType = dfa.getTokenType();
    String token = dfa.getToken();
    tokenTuple = new TokenTuple(tokenType, token);
  }
}
