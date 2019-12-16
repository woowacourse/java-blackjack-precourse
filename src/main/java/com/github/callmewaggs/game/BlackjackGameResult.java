package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Participant;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGameResult {

  private Map<String, Long> participantsIncome;

  public BlackjackGameResult(Dealer dealer, List<Player> players) {
    participantsIncome = new LinkedHashMap<>();
    for (Player player : players) {
      participantsIncome.put(player.getName(), -player.getBettingMoney());
    }
    participantsIncome.put(dealer.getName(), Participant.DEALER_INITIAL_INCOME);
  }
}
