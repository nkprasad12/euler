package problems.problems001to100.problems051to060;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import utils.cards.Card;
import utils.cards.poker.PokerHand;
import utils.generators.Generators;
import utils.generators.base.tuples.Tuples;
import utils.generators.base.tuples.Tuples.Pair;

public class Problem54 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Generators.fromTextFile("problem54.txt", "\\n")
        .mapToPair(input -> toHands(input))
        .filter((hand, other) -> hand.compareTo(other) > 0)
        .reduceAndPrint(0, (count, next) -> count + 1);

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  private static final Pair<PokerHand, PokerHand> toHands(String input) {
    List<Card> firstHand = new ArrayList<>();
    List<Card> secondHand = new ArrayList<>();
    String[] cards = input.split(" ");
    for (int i = 0; i < 10; i++) {
      if (i < 5) {
        firstHand.add(Card.fromString(cards[i]));
      } else {
        secondHand.add(Card.fromString(cards[i]));
      }
    }
    return Tuples.pair(new PokerHand(firstHand), new PokerHand(secondHand));
  }
}
