package domain.card;

import java.util.List;
import java.util.Random;

/**
 * 카드 덱을 관리하는 객체
 */
public class CardDeck {
    List<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        cardDeck = cards;
    }

    /**
     * 덱에서 랜덤한 카드를 한장 뽑는 메소드
     *
     * @return Card 객체
     */
    public Card drawCard() {
        int randomIndex = new Random().nextInt(cardDeck.size());

        Card card = cardDeck.get(randomIndex);
        cardDeck.remove(randomIndex);
        return card;
    }
}
