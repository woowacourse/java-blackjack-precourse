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
    protected String getAllCardsInfo() {
        StringBuffer sb = new StringBuffer();
        for (Card card : cards) {
            sb.append(card);
            sb.append(' ');
        }
        return sb.toString();
    }

    @Override
    protected int getScoreTo(int index) {
        return 0;
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
