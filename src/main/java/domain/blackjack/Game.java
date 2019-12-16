package domain.blackjack;

import controller.IOController;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;

public class Game {

  private Table table;

  public void play() {
    this.table = new Table();

    String[] users = IOController.getUsers();
    double[] betting = IOController.getBetting(users);

    this.table.joinPlayers(users, betting);

    start();
  }

  private void start() {
    this.table.dealCards();
    this.table.displayCards();

    if (dealerHasBlackJack(this.table.getDealer())) {
      endGame();
      return;
    }

    proceedPlayersTruns();

    endGame();
  }

  private void proceedPlayerTurn(Player player, boolean keepProceeding) {
    IOController.printHandsOfPlayer(player);
    System.out.println();

    if (!keepProceeding || Rule.isBlackJack(player.getHands()) || Rule
        .isOverTwenty(player.getHands())) {
      return;
    }

    IOController.askAction(player.getName());
    String action = IOController.getAction();
    boolean proceedAgain = this.table.playerAct(player, action);

    proceedPlayerTurn(player, proceedAgain);
  }


  private void proceedPlayersTruns() {
    ArrayList<Player> players = this.table.getPlayers();

    for (int i = 0; i < players.size(); i++) {
      proceedPlayerTurn(players.get(i), true);
    }

    proceedDealerTurn(this.table.getDealer());

  }

  private void proceedDealerTurn(Dealer dealer) {
    IOController.printHandsOfDealer(dealer, false);
    System.out.println();

    String action = Rule.decideDealerAction(dealer.getHands());

    this.table.dealerAct(dealer, action);

    if (action.equals("HIT")) {
      proceedDealerTurn(dealer);
    }
  }

  private boolean dealerHasBlackJack(Dealer dealer) {
    return Rule.isBlackJack(dealer.getHands());
  }

  private void endGame() {
    IOController.printGameResult(this.table.getDealer(), this.table.getPlayers());

  }

}
