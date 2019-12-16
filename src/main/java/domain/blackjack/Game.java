package domain.blackjack;

public class Game {

  public Table table;

  public void play() {
    this.table = new Table();
    this.table.setTable();
  }

}
