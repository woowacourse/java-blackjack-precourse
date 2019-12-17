package application.domain.user;

import application.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User{
    private static final int ADDING_BOUNDARY = 16;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String getFirstShuffleCardInfo() {
        return cards.get(0).toString();
    }

    // TODO 추가 기능 구현

    @Override
    public String getAllCardsInfo() {
        StringBuffer sb = new StringBuffer();
        for (Card card : cards) {
            sb.append(card);
            sb.append(' ');
        }
        return sb.toString();
    }

    @Override
    protected int getScoreTo(int index) {
        int ret = addAllCardValue(index);
        if (containsA() && ret + PLUS <= BLACK_JACK) {
            return ret + PLUS;
        }
        if (ret > BLACK_JACK) {
            return 0;
        }
        return ret;
    }

    private boolean containsA() {
        return cards.stream()
                .anyMatch(card -> card.getScore() == 1);
    }

    private int addAllCardValue(int index) {
        int ret = 0;
        for (int i = 0; i <= index; i++) {
            Card card = cards.get(i);
            ret += card.getScore();
        }
        return ret;
    }


    @Override
    protected boolean isBlackJack() {
        return getScoreTo(1) == BLACK_JACK;
    }

    @Override
    protected boolean isBust() {
        return getScoreTo(cards.size() - 1) == BUST;
    }

    @Override
    protected boolean isSatisfiedAddingCardCondition() {
        return cards.size() == 2 && getScoreTo(1) <= ADDING_BOUNDARY;
    }
}
