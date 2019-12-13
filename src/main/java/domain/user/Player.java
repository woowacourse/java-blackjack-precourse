package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player implements User {
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    @Override
    public int getScoreOfCards() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    @Override
    public boolean isBust() {
        return getScoreOfCards() > BLACKJACK_NUMBER;
    }

    @Override
    public boolean isBlackjack() {
        return getScoreOfCards() == BLACKJACK_NUMBER;
    }
}
