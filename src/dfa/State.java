package dfa;

public interface State {
  public boolean isAcceptState();
  public void addDestination(String tag, int destination);
  public int getDestination(String input);
  public String getText();
}
