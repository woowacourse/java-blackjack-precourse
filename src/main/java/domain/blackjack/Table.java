package domain.blackjack;


public class Table {

  public Deck deck;

  public void setTable() {
    this.deck = new Deck();
    this.deck.shuffle();
  }

}
