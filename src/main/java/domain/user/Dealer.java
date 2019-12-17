package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {

    private static final String NAME = "딜러";
    private static final int ONEMORE_CARD_BASIS = 16;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateSum() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    public boolean isBlackJack() {
        return isBlackJack(cards);
    }

    public boolean isUnberSixteen() {
        return calculateSum() <= ONEMORE_CARD_BASIS;
    }

    public boolean isBursted() {
        return isBursted(cards);
    }

    public boolean isSameScore(int score) {
        return calculateSum() == score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return NAME;
    }

}
