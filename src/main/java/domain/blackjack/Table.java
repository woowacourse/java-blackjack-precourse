package domain.blackjack;


import controller.IOController;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;

public class Table {

  private final String HIT = "HIT";
  private final String STAY = "STAY";
  private Deck deck;
  private Dealer dealer;
  private ArrayList<Player> players = new ArrayList<>();
  private ArrayList<Double> bettingBox = new ArrayList<>();

  public Table() {
    this.dealer = new Dealer();
    this.deck = new Deck();
    this.deck.shuffle();
  }

  private void addPlayer(Player player) {
    this.players.add(player);
  }

  private void betMoney(double betting) {
    this.bettingBox.add(betting);
  }

  public void joinPlayers(String[] users, double[] betting) {
    for (int i = 0; i < users.length; i++) {
      Player player = new Player(users[i], betting[i]);

      addPlayer(player);
      betMoney(betting[i]);
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
      System.out.println();
    }
  }

  public void displayCards() {
    IOController.printHandsOfDealer(this.dealer, true);
    for (int i = 0; i < this.players.size(); i++) {
      IOController.printHandsOfPlayer(this.players.get(i));
    }
  }

  public Dealer getDealer() {
    return this.dealer;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  public Boolean playerAct(Player player, String action) {
    if (action == HIT) {
      dealCardToPlayer(player);
      return true;
    }

    return false;
  }

}
