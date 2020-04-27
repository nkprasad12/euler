package utils.cards.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import utils.cards.CardValue;

final class PokerHandData implements Comparable<PokerHandData> {

  private final PokerHandResult result;
  private final CardValue resultValue;
  private final Optional<CardValue> resultSecondaryValue;
  private final List<CardValue> remainingCardValues;

  PokerHandData(
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

  PokerHandData(
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