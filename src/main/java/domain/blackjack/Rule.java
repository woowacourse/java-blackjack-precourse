package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import java.util.ArrayList;
import java.util.List;

public class Rule {

  private static final String HIT = "HIT";
  private static final String STAY = "STAY";

  private static final List<Card> Aces = new ArrayList<>(CardFactory.createAces());

  public static Boolean isBlackJack(List<Card> cards) {
    int firstCardScore = cards.get(0).getScore();
    int secondCardScore = cards.get(1).getScore();

    if ((firstCardScore == 11 && secondCardScore == 10) || (firstCardScore == 10
        && secondCardScore == 11)) {
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

  private static Boolean isOverTwentyOne(List<Card> cards) {
    int score = 0;

    for (int i = 0; i < cards.size(); i++) {
      score += cards.get(i).getScore();
    }

    if (score > 21) {
      return true;
    }

    return false;
  }

  private static boolean hasAce(List<Card> cards) {
    if (cards.contains(Aces.get(0)) || cards.contains(Aces.get(1)) || cards.contains(Aces.get(2))
        || cards.contains(Aces.get(3))) {
      return true;
    }
    return false;
  }

  public static int getScore(List<Card> cards) {
    int score = 0;

    for (int i = 0; i < cards.size(); i++) {
      score += cards.get(i).getScore();
    }

    if (hasAce(cards) && isOverTwentyOne(cards)) {
      score -= 10;
    }

    return score;
  }

  private static Boolean isOverSixTeen(List<Card> cards) {
    int score = 0;

    for (int i = 0; i < cards.size(); i++) {
      score += cards.get(i).getScore();
    }

    if (score > 16) {
      return true;
    }
    return false;
  }

  public static String decideDealerAction(List<Card> cards) {
    if (isOverSixTeen(cards)) {
      return STAY;
    }
    return HIT;
  }
}
