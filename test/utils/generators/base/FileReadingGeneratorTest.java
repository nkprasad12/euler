package utils.generators.base;

import static assertions.Assertions.assertGenerates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import utils.generators.Generator;

public class FileReadingGeneratorTest {

  private String TEST_FILE = "tmp_FileReadingGeneatorTest.txt";

  @Test
  public void fileReadingGenerator_givesAllContents() throws IOException {
    writeToFile(TEST_FILE, Arrays.asList("first", "second third"));

    Generator<String> generator = new FileReadingGenerator(TEST_FILE, null);
    assertGenerates(generator, "first", "second", "third");
  }

  @Test
  public void fileReadingGenerator_withDelimiter_givesAllContents() throws IOException {
    writeToFile(TEST_FILE, Arrays.asList("first,second,third"));

    Generator<String> generator = new FileReadingGenerator(TEST_FILE, ",|\\p{javaWhitespace}+");
    assertGenerates(generator, "first", "second", "third");
  }

  static void writeToFile(String path, List<String> contents) throws IOException {
    Path file = Paths.get(path);
    Files.write(file, contents);
  }
}
