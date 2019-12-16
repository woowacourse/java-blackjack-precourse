package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.List;

public class BlackjackGame {

  private Dealer dealer;
  private List<Player> players;
  private BlackjackRule blackjackRule;
  private BlackjackGameResult gameResult;

  public BlackjackGame(Dealer dealer, List<Player> players, List<Card> cards) {
    this.dealer = dealer;
    this.players = players;
    this.blackjackRule = new BlackjackRule(cards);
    this.gameResult = new BlackjackGameResult(dealer, players);
  }

  public void gameStart() {
    blackjackRule.dealInitialCards(dealer, players);
    if (isThereBlackjack()) {
    }
    blackjackRule.askHitOrStay(dealer, players);
    if (isDealerBust()) {
      dealerLose();
      return;
    }
    judgeWinOrLose();
  }

  private void dealerLose() {
    IOHelper.printFinalResultMessage();
    for (Player player : players) {
      gameResult.playerWin(dealer, player);
    }
    gameResult.printIncomes();
  }

  private boolean isDealerBust() {
    return dealer.isBust();
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
