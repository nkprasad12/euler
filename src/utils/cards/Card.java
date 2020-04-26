package src.utils.cards;

/** Represents a playing card. */
public final class Card {

  public static Card fromString(String str) {
    return new Card(Suit.forValue(str.charAt(1)), CardValue.forValue(str.charAt(0)));
  }

  private final Suit suit;
  private final CardValue cardValue;

  public Card(Suit suit, CardValue cardValue) {
    this.suit = suit;
    this.cardValue = cardValue;
  }

  public Suit suit() {
    return suit;
  }

  public CardValue value() {
    return cardValue;
  }
}
