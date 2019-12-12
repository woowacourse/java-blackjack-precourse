package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    protected static final int SCORE_LIMIT = 21;
    protected static final int REDRAW_LIMIT = 16;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getFirstCardString() {
        return ("딜러: " + cards.get(0).toKorean());
    }

    public String getFinalCardString() {
        return ("딜러 카드: " + concatCardList() + " - 결과: "+this.calculateScore()+this.printBust());
    }

    private String concatCardList() {
        return String.join(", ", getKoreanName());
    }

    private List<String> getKoreanName() {
        List<String> names = new ArrayList<>();
        cards.stream().forEach(x -> names.add(x.toKorean()));
        return names;
    }

    private int calculateScore() {
        int score = (cards.stream()
                .mapToInt(x -> x.getScore())
                .sum());
        if(cards.stream().filter(x -> x.getScore() == 1).count() != 0
                && score <= 11) {
            score += 10;
        }
        return score;
    }

    private String printBust() {
        if(this.isBusted()) {
            return (" (버스트)");
        }
        return "";
    }

    public boolean isBlackJack() {
        return (cards.size() == 2 && calculateScore() == SCORE_LIMIT);
    }

    public boolean isBelowRedraw() {
        return (calculateScore() <= REDRAW_LIMIT);
    }

    public boolean isBusted() {
        return (calculateScore() > SCORE_LIMIT);
    }
}
