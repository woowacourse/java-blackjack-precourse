package com.github.callmewaggs.game.domain.user;

import com.github.callmewaggs.game.IOHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PlayerFactory {

  public static List<Player> preparePlayers() {
    List<Player> players;
    do {
      players = tryToCreatePlayers();
    } while (players == null);
    return players;
  }

  private static List<Player> tryToCreatePlayers() {
    try {
      return createPlayers();
    } catch (Exception e) {
      IOHelper.printExceptionMessage(e.getMessage());
      return null;
    }
  }

  private static List<Player> createPlayers() {
    List<Player> players = new ArrayList<>();

    String inputPlayerNames = IOHelper.inputPlayerNames();
    String[] parsed = inputPlayerNames.split(",");

    Set<String> distinctNames = new LinkedHashSet<>(Arrays.asList(parsed));

    if (parsed.length != distinctNames.size() || distinctNames.contains("딜러")) {
      throw new IllegalArgumentException("플레이어의 이름이 잘못되었습니다. 다시 입력해주세요.");
    }
    createPlayersByDistinctNames(players, distinctNames);
    return players;
  }

  private static void createPlayersByDistinctNames(List<Player> players, Set<String> distinctNames) {
    for (String inputPlayerName : distinctNames) {
      createPlayer(players, inputPlayerName);
    }
  }

  private static void createPlayer(List<Player> players, String inputPlayerName) {
    String inputBettingMoney = IOHelper.inputBettingMoney(inputPlayerName);
    players.add(Player.createPlayerIfValidate(inputPlayerName, inputBettingMoney));
  }
}
