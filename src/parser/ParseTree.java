package parser;

import scanner.TokenTuple;

import java.util.ArrayList;
import java.util.List;

public class ParseTree {
  private ParseTreeNode currentNode;

  public ParseTree() {
    currentNode = new ParseTreeNode(new TokenTuple("<program>", ""), null);
  }

  public void addBranch(TokenTuple token) {
    currentNode.addChild(token);
  }

  public boolean equals(Object o) {
    if (!(o instanceof ParseTree))
      return false;
    ParseTree t = (ParseTree)o;
    return currentNode.equals(t.currentNode);
  }

  public String toString() {
    return currentNode.toString();
  }

  public void moveDown() {
    currentNode = currentNode.getLastChild();
  }

  public void moveUp() {
    currentNode = currentNode.getParent();
  }

  private class ParseTreeNode {
    private TokenTuple token;
    private ParseTreeNode parent;
    private List<ParseTreeNode> children;

    ParseTreeNode(TokenTuple token, ParseTreeNode parent) {
      this.token = token;
      this.parent = parent;
      children = new ArrayList<ParseTreeNode>();
    }

    void addChild(TokenTuple token) {
      children.add(new ParseTreeNode(token, this));
    }

    public boolean equals(Object o) {
      if (!(o instanceof ParseTreeNode))
        return false;
      ParseTreeNode n = (ParseTreeNode)o;
      return token.equals(n.token) && children.equals(n.children);
    }

    public String toString() {
      String s = "";
      for (ParseTreeNode n: children)
        s += n.token.toString() + " " + n.children.toString() + ", ";
      return s;
    }

    public ParseTreeNode getLastChild() {
      return children.get(children.size()-1);
    }

    public ParseTreeNode getParent() {
      return parent;
    }
  }
}

