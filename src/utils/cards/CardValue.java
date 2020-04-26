package src.utils.cards;

/** Value of a playing card. */
public enum CardValue {
  Two(2),
  Three(3),
  Four(4),
  Five(5),
  Six(6),
  Seven(7),
  Eight(8),
  Nine(9),
  Ten(10),
  Jack(11),
  Queen(12),
  King(13),
  Ace(14);

  private int cardValue;

  private CardValue(int value) {
    this.cardValue = value;
  }

  public int cardValue() {
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
