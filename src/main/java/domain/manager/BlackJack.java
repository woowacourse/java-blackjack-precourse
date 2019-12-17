package domain.manager;

import java.util.ArrayList;
import java.util.List;

import domain.card.*;
import domain.user.*;

public class BlackJack {
    private Dealer dealer;
    private List<Player> user;
    private Deck deck;

    public BlackJack() {
        user = new ArrayList<>();
        user.add(new Dealer());
        user.addAll(Input.setPlayerList());
        dealer = (Dealer)user.get(0);
        deck = new Deck();
    }

    public void start() {
        for (Player player : user) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
            player.showCards();
        }
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.start();
    }
}