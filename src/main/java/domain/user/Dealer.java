package domain.user;

import domain.card.Card;
import domain.function.CardScoreCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends CardScoreCalculator {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public void printFirstCardOnly() {
        String firstCardName = cards.get(0).getName();
        System.out.println(String.format("딜러 카드: %s", firstCardName));
    }

    private String getAllCardNames() {
        List<String> allCardNames = new ArrayList<>();
        for (Card card : cards) {
            allCardNames.add(card.getName());
        }
        return String.join(", ", allCardNames);
    }

    public void printAllCards() {
        String cardNames = getAllCardNames();
        System.out.print(String.format("딜러 카드: %s", cardNames));
    }

    public int getTotalScore() {
        return calculateTotalScore(cards);
    }

    public void printTotalScore() {
        printAllCards();
        int totalScore = calculateTotalScore(cards);
        System.out.println(String.format(" - 결과: %d", totalScore));
    }

}
