package domain.function;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;

public class CardDistributor {
    private static ArrayList<Card> gameCards;

    public CardDistributor() {
        setGameCards();
    }

    private static void setGameCards() {
        gameCards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(gameCards);
    }

    public void giveCardToDealer(int count, Dealer dealer) {
        while (count-- > 0) {
            dealer.addCard(gameCards.get(0));
            gameCards.remove(0);
        }
    }

    public void giveCardToPlayer(int count, Player player) {
        while (count-- > 0) {
            player.addCard(gameCards.get(0));
            gameCards.remove(0);
        }
    }

}
