package domain.function;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.BlackjackUser;

import java.util.Collections;
import java.util.LinkedList;

public class CardDistributor {
    private LinkedList<Card> gameCards = createGameCards();

    private static LinkedList<Card> createGameCards() {
        LinkedList<Card> gameCards = new LinkedList<>(CardFactory.create());
        Collections.shuffle(gameCards);
        return gameCards;
    }

    public void giveCardToBlackjackUser(BlackjackUser blackjackUser) {
        blackjackUser.addCard(gameCards.poll());
    }

    public void giveTwoCardsToBlackjackUser(BlackjackUser blackjackUser) {
        for (int i=0; i<2; i++) {
            blackjackUser.addCard(gameCards.poll());
        }
    }

}
