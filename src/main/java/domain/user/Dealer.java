package domain.user;

import domain.card.Card;

import java.util.List;
import java.util.StringJoiner;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer {

    public Dealer() {
    }

    public String toString() {
        return "딜러 : " + cardsToString();
    }

    public String cardsToString() {
        List<Card> cards = getCards();
        StringJoiner stringJoiner = new StringJoiner(", ");
        int hiddenCardIndex = 0;
        for (int i = hiddenCardIndex + 1; i < cards.size(); i++) {
            stringJoiner.add(cards.get(i).toString());
        }
        return stringJoiner.toString();
    }

    public String getHiddenCard() {
        int hiddenCardIndex = 0;
        return getCards().get(hiddenCardIndex).toString();
    }
}
