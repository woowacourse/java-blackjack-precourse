package domain.blackjack;

import controller.IOController;

public class Game {

  public Table table;

  public void play() {
    this.table = new Table();
    this.table.setTable();

    String[] users = IOController.getUsers();

  }

}
