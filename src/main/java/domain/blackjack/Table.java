package domain.blackjack;


import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;

public class Table {

  private Deck deck;
  private Dealer dealer;
  private ArrayList<Player> players = new ArrayList<>();

  public Table() {
    this.dealer = new Dealer();
    this.deck = new Deck();
    this.deck.shuffle();
  }

  public void joinPlayers(String[] users, double[] betting) {
    for (int i = 0; i < users.length; i++) {
      this.players.add(new Player(users[i], betting[i]));
    }
  }

}
