package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;

public class Game {
    private final int FIRST_TIME_CARD_COUNT = 2;


    public Game() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    public void startGame() {
        Dealer dealer = new Dealer();
        Utility utility = new Utility();
        Deck deck = new Deck();
        List<Player> playerList = utility.getPlayers();
        this.playFirstGame(dealer, playerList, deck);

        if(utility.isGameOver(dealer)){
            utility.getResult(dealer,playerList);
        }
        dealer.checkDrawAgain(deck);
        play(dealer, playerList, deck);
        utility.getResult(dealer,playerList);
        utility.getResult(dealer,playerList);
    }


    private void playFirstGame(Dealer dealer, List<Player> playerList, Deck deck) {
        this.getFirstCard(dealer, playerList, deck);
        this.printFirstCard(dealer, playerList, deck);
    }

    private void getFirstCard(Dealer dealer, List<Player> playerList, Deck deck) {
        for(int i = 0; i < FIRST_TIME_CARD_COUNT; ++i) {
            Card card = deck.draw();
            dealer.addCard(card);
        }

        for(Player player:playerList){
            for(int i = 0; i < FIRST_TIME_CARD_COUNT; ++i) {
                Card card = deck.draw();
                player.addCard(card);
            }
        }

    }

    public void printFirstCard(Dealer dealer, List<Player> playerList, Deck deck) {
        dealer.showCards();
        for(Player player: playerList){
            player.showCards();
        }
    }

    public void play(Dealer dealer,List<Player> playerList, Deck deck) {
        dealer.checkDrawAgain(deck);
        for(Player player: playerList){
            player.checkDrawAgain(deck);
        }
    }
}
