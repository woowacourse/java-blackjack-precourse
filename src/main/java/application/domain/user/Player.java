package application.domain.user;

import application.domain.card.Card;
import application.view.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    @Override
    public String getFirstShuffleCardInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append(cards.get(0));
        sb.append(',');
        sb.append(cards.get(1));
        return sb.toString();
    }

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
        return Input.isAddingCardFlag();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", bettingMoney=" + bettingMoney +
                ", cards=" + cards +
                '}';
    }
}
