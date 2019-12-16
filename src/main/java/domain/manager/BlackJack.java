package domain.manager;

import java.util.List;

import domain.card.*;
import domain.user.*;

public class BlackJack {
    private Dealer dealer;
    private List<Player> user;
    private Deck deck;

    public BlackJack() {
        dealer = new Dealer();
        user = Input.setPlayerList();
        deck = new Deck();
    }

    public void start() {
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
        for (Player player : user) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
        }
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.start();
    }
}