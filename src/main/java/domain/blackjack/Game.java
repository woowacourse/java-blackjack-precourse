package domain.blackjack;

import controller.IOController;
import domain.user.Dealer;

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

    endGame();
  }

  private Boolean dealerHasBlackJack(Dealer dealer) {
    return Rule.isBlackJack(dealer.getHands());
  }

  private void endGame() {

  }

}
