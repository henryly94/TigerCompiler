package dfa;

public class State {

  private final String text;
  private final int comma;
  private final int colon;
  private final int semi;
  private final int lparen;
  private final int rparen;
  private final int lbrack;
  private final int rbrack;
  private final int lbrace;
  private final int rbrace;
  private final int period;
  private final int plus;
  private final int minus;
  private final int mult;
  private final int div;
  private final int eq;
  private final int less;
  private final int great;
  private final int and;
  private final int or;
  private final int quote;
  private final int forwardslash;
  private final int hat;
  private final int otherPuncs;
  private final int a;
  private final int b;
  private final int c;
  private final int d;
  private final int e;
  private final int f;
  private final int h;
  private final int i;
  private final int k;
  private final int l;
  private final int n;
  private final int o;
  private final int p;
  private final int r;
  private final int s;
  private final int t;
  private final int u;
  private final int v;
  private final int w;
  private final int y;
  private final int otherLower;
  private final int upper;
  private final int underscore;
  private final int num;
  private final int space;
  private final boolean accept;

  public State(String text,
               int comma,
               int colon,
               int semi,
               int lparen,
               int rparen,
               int lbrack,
               int rbrack,
               int lbrace,
               int rbrace,
               int period,
               int plus,
               int minus,
               int mult,
               int div,
               int eq,
               int less,
               int great,
               int and,
               int or,
               int quote,
               int forwardslash,
               int hat,
               int otherPuncs,
               int a,
               int b,
               int c,
               int d,
               int e,
               int f,
               int h,
               int i,
               int k,
               int l,
               int n,
               int o,
               int p,
               int r,
               int s,
               int t,
               int u,
               int v,
               int w,
               int y,
               int otherLower,
               int upper,
               int underscore,
               int num,
               int space,
               boolean accept) {
    this.text = text;
    this.comma = comma;
    this.colon = colon;
    this.semi = semi;
    this.lparen = lparen;
    this.rparen = rparen;
    this.lbrack = lbrack;
    this.rbrack = rbrack;
    this.lbrace = lbrace;
    this.rbrace = rbrace;
    this.period = period;
    this.plus = plus;
    this.minus = minus;
    this.mult = mult;
    this.div = div;
    this.eq = eq;
    this.less = less;
    this.great = great;
    this.and = and;
    this.or = or;
    this.quote = quote;
    this.forwardslash = forwardslash;
    this.hat = hat;
    this.otherPuncs = otherPuncs;
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    this.e = e;
    this.f = f;
    this.h = h;
    this.i = i;
    this.k = k;
    this.l = l;
    this.n = n;
    this.o = o;
    this.p = p;
    this.r = r;
    this.s = s;
    this.t = t;
    this.u = u;
    this.v = v;
    this.w = w;
    this.y = y;
    this.otherLower = otherLower;
    this.upper = upper;
    this.underscore = underscore;
    this.num = num;
    this.space = space;
    this.accept = accept;
  }

  public boolean isAcceptState() {
    return accept;
  }

  public String getText() {
    return text;
  }

  public int getDestination(char ch) {
    switch(ch) {
      case ',': return comma;
      case ':': return colon;
      case ';': return semi;
      case '(': return lparen;
      case ')': return rparen;
      case '[': return lbrack;
      case ']': return rbrack;
      case '{': return lbrace;
      case '}': return rbrace;
      case '.': return period;
      case '+': return plus;
      case '-': return minus;
      case '*': return mult;
      case '/': return div;
      case '=': return eq;
      case '<': return less;
      case '>': return great;
      case '&': return and;
      case '|': return or;
      case '"': return quote;
      case '\\': return forwardslash;
      case '^': return hat;
      case '~':
      case '`':
      case '!':
      case '@':
      case '#':
      case '$':
      case '%':
      case '?': return otherPuncs;
      case 'a': return a;
      case 'b': return b;
      case 'c': return c;
      case 'd': return d;
      case 'e': return e;
      case 'f': return f;
      case 'h': return h;
      case 'i': return i;
      case 'k': return k;
      case 'l': return l;
      case 'n': return n;
      case 'o': return o;
      case 'p': return p;
      case 'r': return r;
      case 's': return s;
      case 't': return t;
      case 'u': return u;
      case 'v': return v;
      case 'w': return w;
      case 'y': return y;
      case 'g':
      case 'j':
      case 'm':
      case 'q':
      case 'x':
      case 'z': return otherLower;
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'K':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'R':
      case 'S':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z': return upper;
      case '_': return underscore;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9': return num;
      case ' ':
      case '\n':
      case '\t': return space;
      default: return -1;
    }
  }
}
