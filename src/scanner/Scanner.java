package scanner;

public class Scanner {

  private final TokenDfa dfa;
  private int lineInd;
  private int charInd;
  private String[] toScan;
  private TokenTuple tokenTuple;

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
    while (charactersRemain() && dfa.isInSpaceState())
      appendNextCharToTokenIfRelevant();
    return charactersRemain();
  }

  /**
   * Finds the next token in the scanned String and returns it. If
   * a lexical error is found, a LexicalException is thrown.
   */
  public TokenTuple getNextToken() {
    tokenTuple = null;
    while (tokenTuple == null) {
      appendNextCharToTokenIfRelevant();
    }
    return tokenTuple;
  }

  private boolean charactersRemain() {
    return lineInd < toScan.length;
  }

  private void handleSpace() {
    dfa.reset();
  }

  private void changeDfaState() {
    dfa.changeState("" + toScan[lineInd].charAt(charInd));
    charInd++;
  }

  private void handleError() {
    dfa.reset();
    throw new LexicalException(lineInd + 1, charInd + 1);
  }

  private boolean noCharsLeftOnCurrentLine() {
    return charInd >= toScan[lineInd].length();
  }

  private void goToNextLine() {
    charInd = 0;
    lineInd++;
  }

  private void appendNextCharToTokenIfRelevant() {
    addNextCharIfRelevant();
    if (noCharsLeftOnCurrentLine())
      goToNextLine();
  }

  private void addNextCharIfRelevant() {
    changeDfaState();
    handleDfaState();
  }

  private void handleDfaState() {
    if (dfa.isInErrorState()) handleError();
    else if (dfa.isInSpaceState()) handleSpace();
    else if (dfa.isInAcceptState()) acceptToken();
  }

  private void acceptToken() {
    tokenTuple = dfa.getToken();
    dfa.reset();
    charInd--;
  }
}
