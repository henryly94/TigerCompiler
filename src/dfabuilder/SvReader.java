package dfabuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SvReader {
  private StringSplitter ss;
  private String[] lines;
  private int currLine;

  public SvReader(char toSplit) {
    ss = new StringSplitter(toSplit);
    currLine = 1;
  }

  public void read(String filename) {
    List<String> lineList = getListOfLines(filename);
    convertListToArray(lineList);
  }

  public String[] getHeader() {
    return ss.split(lines[0]);
  }

  public String[] getLine() {
    currLine++;
    return ss.split(lines[currLine-1]);
  }

  public boolean hasLine() {
    return currLine < lines.length;
  }

  private List<String> getListOfLines(String filename) {
    List<String> lineList = new ArrayList<String>();
    BufferedReader br = getBufferedReader(filename);
    addLines(lineList, br);
    return lineList;
  }

  private void convertListToArray(List<String> lineList) {
    lines = new String[lineList.size()];
    lines = lineList.toArray(lines);
  }

  private BufferedReader getBufferedReader(String filename) {
    try {
      return new BufferedReader((new FileReader(new File(filename))));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
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
}

