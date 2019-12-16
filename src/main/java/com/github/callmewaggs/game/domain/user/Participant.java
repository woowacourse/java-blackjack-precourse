package com.github.callmewaggs.game.domain.user;

import com.github.callmewaggs.game.BlackjackRule;
import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.card.CardState;

public abstract class Participant {

  private final CardState cardState = new CardState();

  public void takeCard(Card card) {
    cardState.addCard(card);
  }

  public String getCurrentCardsInfo() {
    return cardState.getCardsInfo();
  }

  public int getCurrentScore() {
    return cardState.getCardsTotalScore();
  }

  public boolean isBlackjack() {
    return cardState.getCardsTotalScore() == BlackjackRule.BLACKJACK_NUMBER;
  }

  public abstract boolean hitOrStay();

  public abstract boolean isBust();

  public abstract String getName();
}
