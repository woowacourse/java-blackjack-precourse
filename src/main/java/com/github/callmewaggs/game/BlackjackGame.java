package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.List;

public class BlackjackGame {

  private Dealer dealer;
  private List<Player> players;
  private BlackjackRule blackjackRule;

  public BlackjackGame(Dealer dealer, List<Player> players, List<Card> cards) {
    this.dealer = dealer;
    this.players = players;
    this.blackjackRule = new BlackjackRule(cards);
  }

  public void gameStart() {
    blackjackRule.dealInitialCards(dealer, players);
    if (isThereBlackjack()) {
      judgeWinOrLose();
      return;
    }
    blackjackRule.askHitOrStay(dealer, players);
    if (isDealerBust()) {
      dealerLose();
      return;
    }
    judgeWinOrLose();
  }

  private boolean isThereBlackjack() {
    for (Player player : players) {
      if (player.isBlackjack()) {
        return true;
      }
    }
    return dealer.isBlackjack();
  }

  private void judgeWinOrLose() {
  }
}
