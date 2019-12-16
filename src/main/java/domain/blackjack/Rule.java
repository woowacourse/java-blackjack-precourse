package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import java.util.ArrayList;
import java.util.List;

public class Rule {

  private static final String HIT = "HIT";
  private static final String STAY = "STAY";

  private static final List<Card> Aces = new ArrayList<>(CardFactory.createAces());

  public static boolean isBlackJack(List<Card> cards) {
    int firstCardScore = cards.get(0).getScore();
    int secondCardScore = cards.get(1).getScore();

    return (firstCardScore == 11 && secondCardScore == 10) || (firstCardScore == 10
        && secondCardScore == 11);
  }

  public static boolean isOverTwenty(List<Card> cards) {
    int score = getScore(cards);

    return score >= 21;
  }

  private static boolean isOverTwentyOne(List<Card> cards) {
    int score = 0;

    for (Card card : cards) {
      score += card.getScore();
    }

    return score > 21;
  }

  private static boolean hasAce(List<Card> cards) {
    return cards.contains(Aces.get(0)) || cards.contains(Aces.get(1)) || cards.contains(Aces.get(2))
        || cards.contains(Aces.get(3));
  }

  public static int getScore(List<Card> cards) {
    int score = 0;

    for (Card card : cards) {
      score += card.getScore();
    }

    if (hasAce(cards) && isOverTwentyOne(cards)) {
      score -= 10;
    }

    return score;
  }

  private static boolean isOverSixTeen(List<Card> cards) {
    int score = 0;

    for (Card card : cards) {
      score += card.getScore();
    }

    if (hasAce(cards) && isOverTwentyOne(cards)) {
      score -= 10;
    }

    return score > 16;
  }

  public static String decideDealerAction(List<Card> cards) {
    if (isOverSixTeen(cards)) {
      return STAY;
    }
    return HIT;
  }
}
