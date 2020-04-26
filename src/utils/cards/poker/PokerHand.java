package src.utils.cards.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import src.utils.cards.Card;
import src.utils.cards.CardValue;
import src.utils.cards.Suit;

public final class PokerHand {

  private List<Card> cardList;

  public PokerHand(List<Card> cards) {
    this.cardList = cards;
  }

  public boolean beats(PokerHand other) {
    evaluate();
    return true;
  }

  void evaluate() {
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
  }

  boolean isFlush() {
    Suit first = cardList.get(0).suit();
    for (Card card : cardList) {
      if (!first.equals(card.suit())) {
        return false;
      }
    }
    return false;
  }
}
