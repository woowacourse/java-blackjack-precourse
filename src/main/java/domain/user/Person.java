package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    protected static final int SCORE_LIMIT = 21;
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }
    public abstract String getCardString();

    public abstract String getFinalCardString();

    protected String listCard(List<String> cardNames) {
        return String.join(", ", cardNames);
    }

    protected List<String> getKoreanName() {
        List<String> names = new ArrayList<>();
        cards.forEach(x -> names.add(x.toKorean()));
        return names;
    }


    public int getScore() {
        return calculateScore();
    }

    protected int calculateScore() {
        int score = (cards.stream()
                .mapToInt(Card::getScore)
                .sum());
        if (cards.stream().anyMatch(x -> x.getScore() == 1)
                && score <= 11) {
            score += 10;
        }
        return score;
    }

    public boolean isBlackJack() {
        return (cards.size() == 2 && calculateScore() == SCORE_LIMIT);
    }

    public boolean isBusted() {
        return (calculateScore() > SCORE_LIMIT);
    }

    protected String concatBust() {
        if (this.isBusted()) {
            return (" (버스트)");
        }
        return "";
    }
}
