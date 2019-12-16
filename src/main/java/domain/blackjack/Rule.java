package domain.blackjack;

import domain.card.Card;
import java.util.List;

public class Rule {

  public static Boolean isBlackJack(List<Card> cards) {
    int firstCardScore = cards.get(0).getScore();
    int secondCardScore = cards.get(1).getScore();

    if ((firstCardScore == 1 && secondCardScore == 10) || (firstCardScore == 10
        && secondCardScore == 1)) {
      return true;
    }

    return false;
  }

  public static Boolean isOverTwenty(List<Card> cards) {
    int score = getScore(cards);

    if (score >= 21) {
      return true;
    }

    return false;
  }

  public static int getScore(List<Card> cards) {
    return 1;
  }
}
