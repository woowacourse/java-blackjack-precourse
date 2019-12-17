package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGameResult {

  private Map<String, Long> participantsIncome;

  public BlackjackGameResult(Dealer dealer, List<Player> players) {
    participantsIncome = new LinkedHashMap<>();
    long dealerInitialIncome = 0;
    for (Player player : players) {
      long playerBettingMoney = player.getBettingMoney();
      participantsIncome.put(player.getName(), -playerBettingMoney);
      dealerInitialIncome += playerBettingMoney;
    }
    participantsIncome.put(dealer.getName(), dealerInitialIncome);
  }

  public void playerWin(Dealer dealer, Player player) {
    participantsIncome.put(player.getName(),
        participantsIncome.get(player.getName()) + player.getBettingMoney() * 2);
    participantsIncome.put(dealer.getName(),
        participantsIncome.get(dealer.getName()) - player.getBettingMoney() * 2);
  }

  public void push(Dealer dealer, Player player) {
    participantsIncome.put(player.getName(),
        participantsIncome.get(player.getName()) + player.getBettingMoney());
    participantsIncome.put(dealer.getName(),
        participantsIncome.get(dealer.getName()) - player.getBettingMoney());
  }

  public void printIncomes() {
    IOHelper.printFinalResultMessage();
    IOHelper.printNameAndIncome(participantsIncome);
  }
}
