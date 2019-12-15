package domain;

import domain.user.*;
import domain.card.*;

import java.util.List;

public class PlayGame {

    Dealer dealer;
    List<Player> players;
    List<Card> cards;
    int usedCardIndex = -1;

    public PlayGame(List<Player> players) {
        dealer = new Dealer();
        this.players = players;
        this.cards = CardFactory.create();
    }

    public void play() {
        shuffleCard();
        shuffleCard();
    }

    private void shuffleCard() {
        dealer.addCard(pickCard());
        usedCardIndex++;
        for (Player player : players) {
            player.addCard(pickCard());
        }
    }

    private Card pickCard() {
        usedCardIndex++;
        return cards.get(usedCardIndex);
    }
}
