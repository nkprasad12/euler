package test.utils.datastructures;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static test.Assertions.assertEqual;
import static test.Assertions.assertNotEqual;

import org.junit.Test;
import src.utils.datastructures.DisjointSet;

public class DisjointSetTest {

  private static final String HI = "Hi";
  private static final String HELLO = "Hello";
  private static final String GREETINGS = "GREETINGS";
  private static final String SALUTATIONS = "SALUTATIONS";

  @Test
  public void add_null_throwsException() {
    DisjointSet<Integer> set = new DisjointSet<>();
    assertThrows(RuntimeException.class, () -> set.add(null));
  }

  @Test
  public void addAll_addsAllToSet() {
    DisjointSet<String> set = new DisjointSet<>();
    set.addAll(HI, HELLO, GREETINGS);

    assertNotNull(set.find(HI));
    assertNotNull(set.find(HELLO));
    assertNotNull(set.find(GREETINGS));
  }

  @Test
  public void add_multipleElements_addsAll() {
    DisjointSet<String> set = new DisjointSet<>();
    set.add(HI);
    set.add(HELLO);

    assertNotNull(set.find(HI));
    assertNotNull(set.find(HELLO));
  }

  @Test
  public void find_notInSet_returnsNull() {
    DisjointSet<String> set = new DisjointSet<>();
    set.add(HI);

    assertNull(set.find(HELLO));
  }

  @Test
  public void find_noUnion_returnsOriginals() {
    DisjointSet<String> set = new DisjointSet<>();
    set.addAll(HI, HELLO);

    assertEqual(HI, set.find(HI));
    assertEqual(HELLO, set.find(HELLO));
  }

  @Test
  public void find_afterUnion_returnsSame() {
    DisjointSet<String> set = new DisjointSet<>();
    set.addAll(HI, HELLO);
    set.union(HI, HELLO);

    assertEqual(set.find(HELLO), set.find(HI));
  }

  @Test
  public void find_afterDisjointUnion_returnsExpected() {
    DisjointSet<String> set = new DisjointSet<>();
    set.addAll(HI, GREETINGS, HELLO);
    set.union(HI, HELLO);

    assertNotEqual(set.find(HELLO), set.find(GREETINGS));
    assertEqual(set.find(HELLO), set.find(HI));
  }

  @Test
  public void union_ofUnionedSets_combinesAll() {
    DisjointSet<String> set = new DisjointSet<>();

    set.addAll(HI, GREETINGS, HELLO);
    set.union(HI, GREETINGS);
    set.add(SALUTATIONS);
    set.union(HELLO, SALUTATIONS);
    set.union(GREETINGS, SALUTATIONS);

    assertEqual(set.find(HI), set.find(HELLO));
    assertEqual(set.find(HELLO), set.find(SALUTATIONS));
    assertEqual(set.find(SALUTATIONS), set.find(GREETINGS));
  }
}
