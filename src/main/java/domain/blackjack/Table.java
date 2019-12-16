package domain.blackjack;


public class Table {

  public Deck deck;

  public Table (){
    this.deck = new Deck();
    this.deck.shuffle();
  }

}
