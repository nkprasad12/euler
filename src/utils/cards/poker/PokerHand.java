package utils.cards.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    Set<Card> usedCards = new HashSet<>();

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

    boolean isFlush = isFlush();

    PokerHandData pokerData = null;
    PokerHandResult result = PokerHandResult.HighCard;
    CardValue resultValue = null;
    CardValue secondaryValue = null;
    List<CardValue> remainingCards = new ArrayList<CardValue>();

    if (isStraight && isFlush) {
      result = PokerHandResult.StraightFlush;
      resultValue = highCard;
      usedCards.addAll(cardList);
    } else if (isNumberOfAKind(occurenceCount, 4) != null) {
      result = PokerHandResult.FourOfAKind;
      resultValue = isNumberOfAKind(occurenceCount, 4);
      for (Card card : cardList) {
        if (card.value().equals(resultValue)) {
          usedCards.add(card);
        }
      }
    } else if (isNumberOfAKind(occurenceCount, 3) != null
        && isNumberOfAKind(occurenceCount, 2) != null) {
      result = PokerHandResult.FullHouse;
      resultValue = isNumberOfAKind(occurenceCount, 3);
      secondaryValue = isNumberOfAKind(occurenceCount, 2);
      usedCards.addAll(cardList);
    } else if (isFlush) {
      result = PokerHandResult.Flush;
      usedCards.addAll(cardList);
    } else if (isStraight) {
      result = PokerHandResult.Straight;
      resultValue = highCard;
      usedCards.addAll(cardList);
    } else if (isNumberOfAKind(occurenceCount, 3) != null) {
      result = PokerHandResult.ThreeOfAKind;
      resultValue = isNumberOfAKind(occurenceCount, 3);
      for (Card card : cardList) {
        if (card.value().equals(resultValue)) {
          usedCards.add(card);
        }
      }
    } else if (isNumberOfAKind(occurenceCount, 2) != null) {
      resultValue = isNumberOfAKind(occurenceCount, 2);
      for (Card card : cardList) {
        if (card.value().equals(resultValue)) {
          usedCards.add(card);
        }
      }
      occurenceCount.put(resultValue, 0);
      secondaryValue = isNumberOfAKind(occurenceCount, 2);
      result = secondaryValue == null ? PokerHandResult.OnePair : PokerHandResult.TwoPair;
      if (secondaryValue != null) {
        for (Card card : cardList) {
          if (card.value().equals(resultValue)) {
            usedCards.add(card);
          }
        }
      }
    } else {
      resultValue = highCard;
    }

    for (Card card : cardList) {
      if (usedCards.contains(card)) {
        continue;
      }
      remainingCards.add(card.value());
    }

    pokerData = new PokerHandData(result, resultValue, secondaryValue, remainingCards);

    return pokerData;
  }

  private CardValue isNumberOfAKind(HashMap<CardValue, Integer> occurenceMap, int n) {
    CardValue hasNOfAKind = null;

    for (Map.Entry<CardValue, Integer> entry : occurenceMap.entrySet()) {
      if (entry.getValue() == n) {
        hasNOfAKind = entry.getKey();
      }
    }

    return hasNOfAKind;
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
}
