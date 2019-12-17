package com.github.callmewaggs.game.domain.card;

import com.github.callmewaggs.game.BlackjackRule;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardState {

  private final List<Card> cards;

  public CardState() {
    this.cards = new ArrayList<>();
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public int getCardsTotalScore() {
    int totalScore = 0;
    boolean isThereAce = false;
    for (Card card : cards) {
      int score = card.getScore();
      isThereAce |= (score == Symbol.ACE.getScore());
      totalScore += score;
    }
    if (isThereAce) {
      return compareCaseWhenAceIsOneToEleven(totalScore);
    }
    return totalScore;
  }

  private int compareCaseWhenAceIsOneToEleven(int whenAceIsOne) {
    int whenAceIsEleven = whenAceIsOne - Symbol.ACE.getScore() + Symbol.ANOTHER_ACE_NUMBER;
    if ((Math.abs(BlackjackRule.BLACKJACK_NUMBER - whenAceIsEleven)
        < Math.abs(BlackjackRule.BLACKJACK_NUMBER - whenAceIsOne))
        && whenAceIsEleven <= BlackjackRule.BLACKJACK_NUMBER) {
      return whenAceIsEleven;
    }
    return whenAceIsOne;
  }

  public String getCardsInfo() {
    return cards.stream().map(Card::getScoreAndType).collect(Collectors.joining(", "));
  }
}
