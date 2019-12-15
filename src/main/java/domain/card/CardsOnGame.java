package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CardsOnGame {
    private static List<Card> cardsForGame;

    public CardsOnGame() {
        shakeCards();
    }

    public List<Card> getCards() {
        return cardsForGame;
    }

    public static void shakeCards() {
        List<Card> originalCards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(originalCards);
        cardsForGame = originalCards;
    }

    public boolean isEmptyDeck() {
        if (cardsForGame.size() == 0) {
            System.out.println("카드가 더이상 없습니다.");
            return false;
        }
        return true;
    }

    public Card pickUpCard() {
        Card pick = cardsForGame.get(cardsForGame.size() - 1);
        cardsForGame.remove(cardsForGame.size() - 1);
        return pick;
    }

}
