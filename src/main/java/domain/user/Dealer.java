package domain.user;

import domain.card.Card;
import utils.ConsoleOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Person {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void printFirstCard() {
        List<Card> firstCard = new ArrayList<>();
        firstCard.add(cards.get(0));
        ConsoleOutput.printCards("딜러", firstCard);
    }

    public void printCards() {
        ConsoleOutput.printCards("딜러", cards);
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
