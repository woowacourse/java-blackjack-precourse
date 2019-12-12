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
        return ("딜러 카드: " + cards.get(0));
    }

    public String getCardString() {
        return ("딜러 카드: " + concatCardList());
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
        return (cards.stream()
                .mapToInt(x -> x.getScore())
                .sum());
    }

    public int getScoreTest() {
        return calculateScore();
    }

    public boolean isBlackJack() {
        return (cards.size() == 2 && calculateScore() == 21);
    }

    public boolean isBelowRedraw() {
        return (calculateScore() <= 16);
    }

    public boolean isBusted() {
        return (calculateScore() > SCORE_LIMIT);
    }

    // TODO 추가 기능 구현
}
