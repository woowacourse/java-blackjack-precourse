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
    this.blackjackRule = new BlackjackRule(dealer, players, cards);
  }

  // TODO : 게임 종료 및 계산하고 결과출력 그리고 테스트코드
  public void gameStart() {
    blackjackRule.dealInitialCards(dealer, players);
    if (dealer.isBlackjack()) {
      blackjackRule.whenDealerIsBlackjack(players);
      return;
    }
    checkPlayersBlackjack();
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

  private void checkPlayersBlackjack() {
    for (Player player : players) {
      if (player.isBlackjack()) {
        blackjackRule.whenPlayerIsBlackjack(player);
        players.remove(player);
      }
    }
  }
}
