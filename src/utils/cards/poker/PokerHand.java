package utils.cards.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import utils.cards.Card;
import utils.cards.CardValue;
import utils.cards.Suit;

public final class PokerHand implements Comparable<PokerHand> {

  private List<Card> cardList;

  public PokerHand(List<Card> cards) {
    this.cardList = cards;
  }

  @Override
  public int compareTo(PokerHand o) {
    return evaluation().compareTo(o.evaluation());
  }

  PokerHandData evaluation() {
    HashMap<CardValue, Integer> occurenceCount = new HashMap<CardValue, Integer>();
    boolean isStraight = true;
    CardValue highCard = CardValue.Two;
    List<Integer> values = new ArrayList<Integer>();

    for (Card card : cardList) {
      values.add(card.value().cardValue());
      if (occurenceCount.containsKey(card.value())) {
        occurenceCount.put(card.value(), occurenceCount.get(card.value()) + 1);
      } else {
        occurenceCount.put(card.value(), Integer.valueOf(1));
      }

      if (card.value().cardValue() > highCard.cardValue()) {
        highCard = card.value();
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
    return null;
  }

  private boolean isFlush() {
    Suit first = cardList.get(0).suit();
    for (Card card : cardList) {
      if (!first.equals(card.suit())) {
        return false;
      }
    }
    return false;
  }

  private static final class PokerHandData implements Comparable<PokerHandData> {

    private final PokerHandResult result;
    private final CardValue resultValue;
    private final Optional<CardValue> resultSecondaryValue;
    private final List<CardValue> remainingCardValues;

    private PokerHandData(
        PokerHandResult result,
        CardValue resultValue,
        CardValue resultSecondaryValue,
        List<CardValue> remainingCardValues) {
      this.result = result;
      this.resultValue = resultValue;
      this.resultSecondaryValue =
          resultSecondaryValue != null ? Optional.of(resultSecondaryValue) : Optional.empty();
      this.remainingCardValues = new ArrayList<>(remainingCardValues);
      Collections.sort(this.remainingCardValues);
      Collections.reverse(this.remainingCardValues);
    }

    private PokerHandData(
        PokerHandResult result, CardValue resultValue, List<CardValue> remainingCardValues) {
      this(result, resultValue, null, remainingCardValues);
    }

    @Override
    public int compareTo(PokerHandData o) {
      if (result.compareTo(o.result) != 0) {
        return result.compareTo(o.result);
      }
      if (resultValue.compareTo(o.resultValue) != 0) {
        return resultValue.compareTo(o.resultValue);
      }
      boolean useSecondary = resultSecondaryValue.isPresent() && o.resultSecondaryValue.isPresent();
      if (useSecondary) {
        int secondaryCompareResult =
            resultSecondaryValue.get().compareTo(o.resultSecondaryValue.get());
        if (secondaryCompareResult != 0) {
          return secondaryCompareResult;
        }
      }
      for (int i = 0; i < remainingCardValues.size(); i++) {
        int compareResult = remainingCardValues.get(i).compareTo(o.remainingCardValues.get(i));
        if (compareResult != 0) {
          return compareResult;
        }
      }
      return 0;
    }
  }
}
