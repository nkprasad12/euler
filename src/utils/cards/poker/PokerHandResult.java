package utils.cards.poker;

/** Possible results for a poker hand. */
public enum PokerHandResult {
  HighCard(1),
  OnePair(2),
  TwoPair(3),
  ThreeOfAKind(4),
  Straight(5),
  Flush(6),
  FullHouse(7),
  FourOfAKind(8),
  StraightFlush(9);

  private final int rank;

  PokerHandResult(int value) {
    this.rank = value;
  }

  public int rank() {
    return rank;
  }
}
