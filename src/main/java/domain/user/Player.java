package domain.user;

import domain.card.Card;
import utils.ConsoleOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Person {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void printCards() {
        ConsoleOutput.printCards(name, cards);
    }

    private int calculateScore() {
        return (cards.stream()
                .mapToInt(x -> x.getScore())
                .sum());
    }

    public int getScoreTest() {
        return calculateScore();
    }

    public boolean isBusted() {
        return (calculateScore() > SCORE_LIMIT);
    }


    // TODO 추가 기능 구현
}
