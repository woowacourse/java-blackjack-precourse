package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardBundle {
    private static final int BLACK_JACK = 21;
    private static final int OPTIONAL_ACE_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardSum() {
        long aceAmount = getAceAmount();
        return getSum(aceAmount);
    }

    private int getSum(long aceAmount) {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        for (int i = 0; i < aceAmount; i++) {
            sum = addAceScore(sum);
        }
        return sum;
    }

    private int addAceScore(int sum) {
        if (sum + OPTIONAL_ACE_SCORE <= BLACK_JACK) {
            return sum + OPTIONAL_ACE_SCORE;
        }
        return sum;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && getCardSum() == BLACK_JACK;
    }

    private long getAceAmount() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isOver() {
        return getCardSum() > BLACK_JACK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardBundle that = (CardBundle) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
