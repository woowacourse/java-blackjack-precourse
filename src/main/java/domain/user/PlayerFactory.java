package domain.user;

import java.util.ArrayList;
import java.util.List;

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
    for (String inputPlayerName : parsed) {
      String inputBettingMoney = IOHelper.inputBettingMoney(inputPlayerName);
      players.add(Player.createPlayerIfValidate(inputPlayerName, inputBettingMoney));
    }
    return players;
  }
}
