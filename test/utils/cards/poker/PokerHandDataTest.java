package utils.cards.poker;

import static org.junit.Assert.assertTrue;
import static utils.cards.CardValue.Five;
import static utils.cards.CardValue.Jack;
import static utils.cards.poker.PokerHandResult.HighCard;
import static utils.cards.poker.PokerHandResult.OnePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import utils.cards.CardValue;

public class PokerHandDataTest {

  private static final List<CardValue> EMPTY = Collections.unmodifiableList(new ArrayList<>());

  @Test
  public void compareTo_highCard_highCard() {
    PokerHandData first = new PokerHandData(HighCard, Five, EMPTY);
    PokerHandData second = new PokerHandData(HighCard, Jack, EMPTY);

    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);
  }

  @Test
  public void compareTo_pair_highCard() {
    PokerHandData first = new PokerHandData(OnePair, Five, EMPTY);
    PokerHandData second = new PokerHandData(HighCard, Jack, EMPTY);

    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);
  }
}