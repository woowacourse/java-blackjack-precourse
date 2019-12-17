package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class BlackjackGameResult {

  private static final int INITIAL_VALUE = 1;
  private static final int DOUBLE_VALUE = 2;
  private static final double HALF_VALUE = 0.5;

  private Map<String, Double> participantsIncome;

  BlackjackGameResult(Dealer dealer, List<Player> players) {
    participantsIncome = new LinkedHashMap<>();
    double dealerInitialIncome = 0;
    for (Player player : players) {
      double playerBettingMoney = player.getBettingMoney();
      participantsIncome.put(player.getName(), -playerBettingMoney);
      dealerInitialIncome += playerBettingMoney;
    }
    participantsIncome.put(dealer.getName(), dealerInitialIncome);
  }

  void playerWin(Dealer dealer, Player player) {
    calculateWithGivenValue(dealer, player, DOUBLE_VALUE);
  }

  void push(Dealer dealer, Player player) {
    calculateWithGivenValue(dealer, player, INITIAL_VALUE);
  }

  void playersBlackjack(Dealer dealer, Player player) {
    calculateWithGivenValue(dealer, player, DOUBLE_VALUE + HALF_VALUE);
  }

  private void calculateWithGivenValue(Dealer dealer, Player player, double value) {
    participantsIncome.put(player.getName(),
        participantsIncome.get(player.getName()) + player.getBettingMoney() * value);
    participantsIncome.put(dealer.getName(),
        participantsIncome.get(dealer.getName()) - player.getBettingMoney() * value);
  }

  void printIncomes() {
    IOHelper.printFinalResultMessage();
    IOHelper.printNameAndIncome(participantsIncome);
  }
}
