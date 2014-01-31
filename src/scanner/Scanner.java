package scanner;

import dfa.Dfa;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
  private Dfa tigerDfa;
  private List<TokenTuple> tokenTuples;

  public Scanner(Dfa tigerDfa) {
    this.tigerDfa = tigerDfa;
  }

  public List<TokenTuple> scan(String toScan) {
    toScan += " ";
    tokenTuples = new ArrayList<TokenTuple>();
    for (int i = 0; i < toScan.length(); i++)
      i = handleNextChar(toScan, i);
    return tokenTuples;
  }

  private int handleNextChar(String toScan, int i) {
    char c = toScan.charAt(i);
    tigerDfa.changeState(c);
    if (tigerDfa.isSpace()) tigerDfa.reset();
    if (tigerDfa.isInAcceptState()) i = acceptToken(i);
    return i;
  }

  private int acceptToken(int i) {
    addToken();
    tigerDfa.reset();
    return i-1;
  }

  private void addToken() {
    String tokenType = tigerDfa.getTokenType();
    String token = tigerDfa.getToken();
    tokenTuples.add(new TokenTuple(tokenType, token));
  }
}
