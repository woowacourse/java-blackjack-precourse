package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.List;

public class BlackjackGame {

  private final Dealer dealer;
  private final List<Player> players;
  private final BlackjackRule blackjackRule;

  public BlackjackGame(Dealer dealer, List<Player> players, BlackjackRule blackjackRule) {
    this.dealer = dealer;
    this.players = players;
    this.blackjackRule = blackjackRule;
  }

  public void gameStart() {
    blackjackRule.dealInitialCards(dealer, players);
    if (dealer.isBlackjack()) {
      blackjackRule.whenDealerIsBlackjack(dealer, players);
      return;
    }
    checkBlackjackForAllPlayers();
    blackjackRule.askHitOrStay(dealer, players);
    if (isDealerBust()) {
      blackjackRule.dealerLose(dealer, players);
      return;
    }
    blackjackRule.judgeWinOrLose(dealer, players);
  }

  private boolean isDealerBust() {
    return dealer.isBust();
  }

  private void checkBlackjackForAllPlayers() {
    for (Player player : players) {
      checkBlackjackByPlayer(dealer, player);
    }
  }

  private void checkBlackjackByPlayer(Dealer dealer, Player player) {
    if (player.isBlackjack()) {
      blackjackRule.whenPlayerIsBlackjack(dealer, player);
      players.remove(player);
    }
  }

  public int getNumberOfPlayers() {
    return players.size();
  }
}
