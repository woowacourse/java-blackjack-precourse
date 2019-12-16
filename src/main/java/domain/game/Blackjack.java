package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private final List<Card> cards;
    public Dealer dealer;
    public List<Player> players;
    public Scanner sc = new Scanner(System.in);

    public Blackjack() {
        cards = CardFactory.create();
        dealer = new Dealer();
    }
}
