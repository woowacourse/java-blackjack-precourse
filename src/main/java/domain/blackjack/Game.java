package domain.blackjack;

import controller.IOController;

public class Game {

  public Table table;

  public void play() {
    this.table = new Table();

    String[] users = IOController.getUsers();
    double[] betting = IOController.getBetting(users);

    this.table.joinPlayers(users, betting);

    start();
  }

  private void start() {
    this.table.dealCards();
//    IOController.printHands(this.table);
  }

}
