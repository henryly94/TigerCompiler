package scanner;

public class TokenTuple {
  final String tokenType;
  final String token;

  public TokenTuple(String tokenType, String token) {
    this.tokenType = tokenType;
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TokenTuple))
      return false;
    TokenTuple t = (TokenTuple)o;
    return tokenType.equals(t.tokenType) && token.equals(t.token);
  }

  @Override
  public String toString() {
    return "token type: " + tokenType + ", token: " + token;
  }
}
