package src.problems.problems051to060;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import src.utils.generators.Generators;
import src.utils.generators.base.tuples.Tuples;
import src.utils.generators.base.tuples.Tuples.Pair;

public class Problem54 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Generators.fromTextFile("problem54.txt", "\\n")
        .mapToPair(input -> toHands(input))
        .filter((hand, other) -> hand.beats(other))
        .reduceAndPrint(0, (count, next) -> count + 1);

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  enum Suit {
      Club,
      Spade,
      Heart,
      Diamond;

    static Suit forValue(Character suit) {
        if (suit == 'C') {
          return Club;
        }
        if (suit == 'S') {
          return Spade;
        }
        if (suit == 'H') {
          return Heart;
        }
        if (suit == 'D') {
          return Diamond;
        }
        throw new RuntimeException("Invalid value " + suit + " for Suit");
    }
  }

  enum CardValue {
    Two (2),
    Three (3),
    Four (4),
    Five (5),
    Six (6),
    Seven (7),
    Eight (8),
    Nine (9),
    Ten (10),
    Jack (11),
    Queen (12),
    King (13),
    Ace (14);

    private int cardValue;

    private CardValue(int value) {
        this.cardValue = value;    
    }

    int cardValue() {
      return cardValue;
    }

    public static CardValue forValue(Character value) {
        if (value == '2') {
          return Two;
        }
        if (value == '3') {
          return Three;
        }
        if (value == '4') {
          return Four;
        }
        if (value == '5') {
          return Five;
        }
        if (value == '6') {
          return Six;
        }
        if (value == '7') {
          return Seven;
        }
        if (value == '8') {
          return Eight;
        }
        if (value == '9') {
          return Nine;
        }
        if (value == 'T') {
        return Ten;
        }
        if (value == 'J') {
        return Jack;
        }
        if (value == 'Q') {
        return Queen;
        }
        if (value == 'K') {
        return King;
        }
        if (value == 'A') {
        return Ace;
        }
        throw new RuntimeException("Invalid value " + value + " for CardValue");
    }
  }

  private static final Pair<Hand, Hand> toHands(String input) {
    List<Card> firstHand = new ArrayList<>();
    List<Card> secondHand = new ArrayList<>();
    String[] cards = input.split(" ");
    for (int i = 0; i < 10; i++) {
      if (i < 5) {
        firstHand.add(new Card(cards[i]));
      } else {
        secondHand.add(new Card(cards[i]));
      }
    }
    return Tuples.pair(new Hand(firstHand), new Hand(secondHand));
  }

  enum HandResultType {
    HighCard (1),
    OnePair (2),
    TwoPair (3),
    ThreeOfAKind (4),
    Straight (5),
    Flush (6),
    FullHouse (7),
    FourOfAKind (8),
    StraightFlush (9);

    final int handResultValue;

    HandResultType(int value) {
      this.handResultValue = value;
    }
  }

  private static final class Hand {
    private List<Card> cardList;
    
    Hand(List<Card> cards) {
      this.cardList = cards;
    }

    void evaluate() {
        HashMap<CardValue, Integer> occurenceCount = new HashMap<CardValue, Integer>();
        boolean isStraight = true;
        CardValue highCard = CardValue.Two;
        List<Integer> values = new ArrayList<Integer>();

        for (Card card : cardList) {
            values.add(card.cardValue.cardValue());
            if (occurenceCount.containsKey(card.cardValue)) {
                occurenceCount.put(card.cardValue, occurenceCount.get(card.cardValue)+1);
            } else {
                occurenceCount.put(card.cardValue, Integer.valueOf(1));
            }

            if (card.cardValue.cardValue() > highCard.cardValue()) {
                highCard = card.cardValue;
            }
        }

        Collections.sort(values);
        int previousValue = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            int value = values.get(i);
            if (value - previousValue != 1) {
                isStraight = false;
                break;
            }
            previousValue = value;
        }

        boolean hi = isFlush() && isStraight;
        System.out.println(hi);
    }

    boolean isFlush() {
      Suit first = cardList.get(0).suit;
      for (Card card : cardList) {
        if (!first.equals(card.suit)) {
          return false;
        }
      }
      return false;
    }

    boolean beats(Hand other) {
      evaluate();
      return true;
    }
  }

  private static final class Card {

    private Suit suit;
    private CardValue cardValue;
    
    Card(String s) {
      // TODO TOPHER logic here;
    }
  }
}
