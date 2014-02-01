package tsvreader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SvReader {
  private String[] lines;
  private int currLine;

  public SvReader() {
    currLine = 1;
  }

  public String[] getHeader() {
    return splitAround(lines[0], ',');
  }

  public String[] getLine() {
    currLine++;
    return splitAround(lines[currLine-1], ',');
  }

  public boolean hasLine() {
    return currLine < lines.length;
  }

  private String[] splitAround(String toSplit, char c) {
    List<String> splitList = split(toSplit, c);
    String[] toReturn = new String[splitList.size()];
    return splitList.toArray(toReturn);
  }

  private List<String> split(String toSplit, char c) {
    List<String> split = new ArrayList<String>();
    for (int i = 0; i < toSplit.length(); i++)
      if (toSplit.charAt(i) == c)
        return doSplit(toSplit, c, split, i);
    split.add(toSplit);
    return split;
  }

  private List<String> doSplit(String toSplit, char c, List<String> split, int i) {
    split.add(toSplit.substring(0, i));
    split.addAll(split(toSplit.substring(i + 1, toSplit.length()), c));
    return split;
  }

  public void read(String filename) {
    List<String> lineList = new ArrayList<String>();
    BufferedReader br = getBufferedReader(filename);
    addLines(lineList, br);
    lines = new String[lineList.size()];
    lines = lineList.toArray(lines);
  }

  private void addLines(List<String> lineList, BufferedReader br) {
    String line;
    try {
      while ((line = br.readLine()) != null)
        lineList.add(line);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private BufferedReader getBufferedReader(String filename) {
    try {
      return new BufferedReader((new FileReader(new File(filename))));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
}
