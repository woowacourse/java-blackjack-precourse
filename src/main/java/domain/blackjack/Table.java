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
  private ArrayList<Double> scoreBoard = new ArrayList<>();

  public Table() {
    this.dealer = new Dealer();
    this.deck = new Deck();
    this.deck.shuffle();
  }

  private void addPlayer(Player player) {
    this.players.add(player);
  }


  public void joinPlayers(String[] users, double[] betting) {
    for (int i = 0; i < users.length; i++) {
      Player player = new Player(users[i], betting[i]);

      addPlayer(player);
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
    System.out.println();
  }

  public Dealer getDealer() {
    return this.dealer;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  public boolean playerAct(Player player, String action) {
    if (action.equals(HIT)) {
      dealCardToPlayer(player);
      return true;
    }
    return false;
  }

  public void dealerAct(Dealer dealer, String action) {
    if (action.equals(HIT)) {
      dealCardToDealer(dealer);
    }
  }

  public void reward() {
    int dealerScore = Rule.getScore(this.dealer.getHands());

    for (int i = 0; i < this.players.size(); i++) {
      reward(this.players.get(i), dealerScore);
    }
  }

  private void reward(Player player, int dealerScore) {
    int score = Rule.getScore(player.getHands());

    if ((dealerScore >= score && dealerScore <= 21) || score > 21) {
      lose(player);
      return;
    }

    win(player);
  }

  private void lose(Player player) {
    double bettingMoney = player.getBettingMoney();
    scoreBoard.add(-bettingMoney);
  }

  private void win(Player player) {
    double bettingMoney = player.getBettingMoney();

    if (Rule.isBlackJack(player.getHands())) {
      scoreBoard.add(Math.floor(bettingMoney * 1.5));
      return;
    }
    scoreBoard.add(bettingMoney);
  }

  public void displayScoreBoard() {
    IOController.printRewardResult(this.players, this.scoreBoard);
  }
}
