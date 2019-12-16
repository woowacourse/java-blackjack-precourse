package domain.blackjack;


import controller.IOController;
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

  private void dealCardToPlayer(Player player) {
    IOController.printDealCardToUser(player.getName());
    player.addCard(this.deck.draw());
  }

  private void dealCardToDealer(Dealer dealer) {
    IOController.printDealCardToDealer();
    dealer.addCard(this.deck.draw());
  }

  private void dealToAll() {
    for (int i = 0; i < this.players.size(); i++) {
      dealCardToPlayer(this.players.get(i));
    }
    dealCardToDealer(this.dealer);
  }

  public void dealCards() {
    for (int i = 0; i < 2; i++) {
      dealToAll();
    }
  }

  public void displayCards() {
    IOController.printHandsOfDealer(this.dealer);
    for (int i = 0; i < this.players.size(); i++) {
      IOController.printHandsOfPlayer(this.players.get(i));
    }
  }

}
