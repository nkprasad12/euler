package src.utils.cards;

/** Suit of a playing card. */
public enum Suit {

    Club,
    Spade,
    Heart,
    Diamond;

    public static Suit forValue(Character suit) {
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