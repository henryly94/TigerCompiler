package frontend;

import dfabuilder.FileScraper;
import parser.Parser;
import scanner.Scanner;
import scanner.TokenTuple;

import java.util.ArrayList;
import java.util.List;

public class FrontEnd {
  private final Scanner scanner;
  private final Parser parser;
  private final FileScraper scraper;

  public FrontEnd(Scanner scanner, Parser parser) {
    this.scanner = scanner;
    this.parser = parser;
    scraper = new FileScraper();
  }

  public boolean compile(String filename) {
    String[] lines = scraper.read(filename);
    List<TokenTuple> tokens = new ArrayList<TokenTuple>();
    scanner.scan(lines);
    while (scanner.hasMoreTokens())
      tokens.add(scanner.getNextToken());

    parser.parse(tokens);

    return parser.isLegal();
  }
}
