package scanner;

class TokenTuple {
  private final String tokenType;
  private final String token;

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
}
