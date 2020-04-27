package utils.generators.base;

import java.io.File;
import java.util.Scanner;
import utils.generators.Generator;

/** Generator that generators tokens from a file. */
public final class FileReadingGenerator implements Generator<String> {

  private final Scanner scanner;

  public FileReadingGenerator(String filePath, String delimiter) {
    try {
      this.scanner = new Scanner(new File(filePath));
      if (delimiter != null) {
        this.scanner.useDelimiter(delimiter);
      }
    } catch (Exception e) {
      throw new RuntimeException("Error while opening file", e);
    }
  }

  @Override
  public String getNext() {
    return scanner.next();
  }

  public boolean hasNext() {
    boolean result = scanner.hasNext();
    if (!result) {
      scanner.close();
    }
    return result;
  }
}
