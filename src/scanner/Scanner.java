package scanner;

public class Scanner {

  private final TokenDfa dfa;
  private String[] toScan;
  private int lineInd;
  private int charInd;

  public Scanner(TokenDfa dfa) {
    this.dfa = dfa;
  }

  public void scan(String[] toScan) {
    initValues(toScan);
    prepareLinesToYieldTokens();
  }

  public void scan(String toScan) {
    scan(new String[]{toScan});
  }

  private void initValues(String[] toScan) {
    this.toScan = toScan;
    lineInd = 0;
    charInd = 0;
  }

  private void prepareLinesToYieldTokens() {
    for (int i = 0; i < toScan.length; i++)
      toScan[i] += " ";
  }

  public boolean hasMoreTokens() {
    removeSpaceChars();
    return charactersRemain();
  }

  private void removeSpaceChars() {
    while (charactersRemain() && dfa.isInSpaceState())
      processNextChar();
  }

  /**
   * Finds the next token in the scanned String and returns it. If
   * a lexical error is found, a LexicalException is thrown.
   */
  public TokenTuple getNextToken() {
    while (!dfa.isInAcceptState())
      processNextChar();
    TokenTuple token = dfa.getToken();
    resetDfaAndMoveBackChar();
    return token;
  }

  private void processNextChar() {
    changeDfaState();
    if (!dfa.isInAcceptState())
      handleNonAcceptState();
  }

  private void resetDfaAndMoveBackChar() {
    dfa.reset();
    charInd--;
  }

  private void handleNonAcceptState() {
    if (dfa.isInErrorState())
      throw new LexicalException(lineInd + 1, charInd + 1);
    else if (dfa.isInSpaceState())
      dfa.reset();
    if (noCharsLeftInLine())
      gotoNextLine();
  }

  private void changeDfaState() {
    dfa.changeState("" + toScan[lineInd].charAt(charInd));
    charInd++;
  }

  private void gotoNextLine() {
    charInd = 0;
    lineInd++;
  }

  private boolean charactersRemain() {
    return lineInd < toScan.length;
  }

  private boolean noCharsLeftInLine() {
    return charInd >= toScan[lineInd].length();
  }

}
