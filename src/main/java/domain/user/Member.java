package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 카드의 멤버들의 생성을 위한 부모 클래스
 */
public abstract class Member {
    private final List<Card> cards = new ArrayList<>();

    public boolean isSurvive() {
        return getOptimizedSum().getScore() <= Score.BLACKJACK_SCORE;
    }

    public Score getOptimizedSum() {
        Score sum = getSum();
        if (hasAce()) {
            sum = sum.plusTenIfNotBust();
        }
        return sum;
    }

    private Score getSum() {
        Score sum = new Score(0);
        for (Card card: cards) {
            Score next = card.getScore();
            sum = sum.plus(next);
        }

        return sum;
    }

    private boolean hasAce() {
        for (Card card: cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String toStringOneCard() {
        return cards.get(0).toString() + '\n';
    }

    public String toStringCards() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card: cards) {
            stringBuilder.append(card.toString());
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public abstract String cardInfo();
}
