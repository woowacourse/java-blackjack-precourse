package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.List;

public class BlackjackGame {

  Dealer dealer;
  List<Player> players;
  BlackjackRule blackjackRule;

  public BlackjackGame(Dealer dealer, List<Player> players, List<Card> cards) {
    this.dealer = dealer;
    this.players = players;
    this.blackjackRule = new BlackjackRule(cards);
  }

  public void gameStart() {
    blackjackRule.dealInitialCards(participants);
    if (isThereBlackjack()) {
      judgeWinOrLose();
      return;
    }
    blackjackRule.hitOrStay(participants);
    if (isDealerBust()) {
      dealerLose();
      return;
    }
    judgeWinOrLose();
  }
}
