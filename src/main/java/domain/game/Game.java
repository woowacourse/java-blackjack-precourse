package domain.game;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.*;

public class Game {
    public static void main(String[] args) {
        List<Card> cards = CardFactory.create();
        Blackjack blackjack = new Blackjack(cards);
        blackjack.ready();
    }
}
