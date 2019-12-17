package domain.user;

import domain.card.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class GameManager {
    private ArrayList<Player> players;
    private Dealer dealer;
    private Stack<Card> deck;

    public GameManager(ArrayList<Player> players, Dealer dealer, Stack<Card> deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    private void pushDeck(Card card) {
        deck.push(card);
    }

    private Card popDeck() {
        Card poped;
        if (deck.isEmpty()) {
            return null;
        }
        poped = deck.pop();

        return poped;
    }

    public void offerCardToAll() {
        Card poped;
        for (Player player: players) {
            poped = popDeck();
            player.addCard(poped);
        }
        poped = popDeck();
        dealer.addCard(poped);
    }

    private void offerCardToPlayer(int index) {
        Card poped = popDeck();
        players.get(index).addCard(poped);
    }

    private void offerCardToDealer() {
        Card poped = popDeck();
        dealer.addCard(poped);
    }

    public String bettingInfos() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player: players) {
            stringBuilder.append(player.bettingInfo());
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public String cardInfosOfAllMemberNotHidden() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.cardInfo());
        for (Player player: players) {
            stringBuilder.append(player.cardInfo());
        }
        return stringBuilder.toString();
    }
}
