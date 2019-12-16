package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

  private void dealerLose() {
    IOHelper.printFinalResultMessage();
    long dealerIncome = 0;
    Map<String, Long> participantsIncome = new LinkedHashMap<>();
    for (Player player : players) {
      participantsIncome.put(player.getName(), player.getBettingMoney());
      dealerIncome -= player.getBettingMoney();
    }
    participantsIncome.put(dealer.getName(), dealerIncome);
    IOHelper.printNameAndIncome(participantsIncome);
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
