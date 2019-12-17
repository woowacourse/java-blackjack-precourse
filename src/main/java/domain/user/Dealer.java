package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int resultScore = 0;
        for (int i = 0; i < cards.size(); i ++) {
            resultScore += this.cards.get(i).getScore();
        }
        return resultScore;
    }

    public String toLog() {
        int publicDealerCardNumber = 1;
        return "딜러 카드: " + cards.get(publicDealerCardNumber);
    }

    @Override
    public String toString() {
        return "딜러 카드: " + cards
                + " - 결과: " + getScore();
    }

}
