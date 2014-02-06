package dfabuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileScraper {

  private String[] lines;

  public String[] read(String filename) {
    List<String> lineList = getListOfLines(filename);
    convertListToArray(lineList);
    return lines;
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
      throw new RuntimeException();
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
