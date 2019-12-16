package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.card.CardShuffler;
import com.github.callmewaggs.game.domain.user.Participant;
import java.util.List;

public class BlackjackGame {
  List<Participant> participants;
  BlackjackRule blackjackRule;

  public BlackjackGame(List<Participant> participants, List<Card> cards) {
    this.participants = participants;
    this.blackjackRule = new BlackjackRule(cards);
  }

  public void gameStart() {
    blackjackRule.dealInitialCards(participants);
    if(isThereBlackjack()) {
      calculateResult();
      return;
    }
    blackjackRule.hitOrStay(participants);
    if(isDealerBust()) {
      calculateResult();
      return;
    }
    calculateResult();
  }
}
