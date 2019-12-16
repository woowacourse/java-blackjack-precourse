package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.card.CardShuffler;
import com.github.callmewaggs.game.domain.user.Participant;
import java.util.List;

public class BlackjackGame {
  List<Participant> participants;
  CardShuffler cardShuffler;
  BlackjackRule blackjackRule;

  public BlackjackGame(List<Participant> participants, List<Card> cards) {
    this.participants = participants;
    this.cardShuffler = new CardShuffler(cards);
    this.blackjackRule = new BlackjackRule();
  }

  public void gameStart() {
    blackjackRule.dealInitialCards(participants, cardShuffler);
    if(isThereBlackjack()) {
      calculateResult();
      return;
    }
    blackjackRule.hitOrStay(participants, cardShuffler);
    if(isDealerBust()) {
      calculateResult();
      return;
    }
    calculateResult();
  }
}
