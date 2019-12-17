package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Stack;

public class GameManager {
    private PlayerManager playerManager;
    private Dealer dealer;
    private Stack<Card> deck;

    public GameManager(PlayerManager playerManager, Dealer dealer, Stack<Card> deck) {
        this.playerManager = playerManager;
        this.dealer = dealer;
        this.deck = deck;
    }

    private void pushDeck(Card card) {
        deck.push(card);
    }

    private Card popDeck() {
        Card pulled;
        if (deck.isEmpty()) {
            return null;
        }
        pulled = deck.pop();

        return pulled;
    }

    public void offerCardToPlayer(int index) {

    }
}
