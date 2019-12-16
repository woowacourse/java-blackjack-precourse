package domain.function;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.BlackjackUser;
import domain.user.Dealer;
import domain.user.Player;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDistributor {
    private LinkedList<Card> gameCards = createGameCards();

    private static LinkedList<Card> createGameCards() {
        LinkedList<Card> gameCards = new LinkedList<>(CardFactory.create());
        Collections.shuffle(gameCards);
        return gameCards;
    }

    public void giveTwoCardsToGameParticipant(Dealer dealer, List<Player> playerList, List<String> playerNameList) {
        giveTwoCardsToBlackjackUser(dealer);
        for (Player player : playerList) {
            giveTwoCardsToBlackjackUser(player);
        }
        Viewer.printDistributedCards(dealer, playerNameList, playerList);
    }

    public void giveCardToBlackjackUser(BlackjackUser blackjackUser) {
        blackjackUser.addCard(gameCards.poll());
    }

    private void giveTwoCardsToBlackjackUser(BlackjackUser blackjackUser) {
        for (int i = 0; i < 2; i++) {
            blackjackUser.addCard(gameCards.poll());
        }
    }

}
