package com.github.callmewaggs.game.domain.user;

import com.github.callmewaggs.game.BlackjackRule;
import com.github.callmewaggs.game.IOHelper;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Participant {

  public Dealer() {
  }

  public boolean isBust() {
    return super.getCurrentScore() > BlackjackRule.BLACKJACK_NUMBER;
  }

  @Override
  public boolean hitOrStay() {
    boolean result = super.getCurrentScore() <= DEALER_MAX_HIT_NUMBER;
    IOHelper.printDealerHitOrStayMessage(result);
    return result;
  }

  @Override
  public String getName() {
    return "딜러";
  }
}
