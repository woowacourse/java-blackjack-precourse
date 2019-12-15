package domain.user;

import domain.card.Card;
import domain.function.CardScoreCalculator;

import java.util.ArrayList;
import java.util.List;

public class BlackjackUser {
    protected final List<Card> cards = new ArrayList<>();
    protected final String name;
    private final CardScoreCalculator cardScoreCalculator =  new CardScoreCalculator();

    public BlackjackUser(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
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
        System.out.print(String.format("%s 카드: %s", name, cardNames));
    }

    public int getTotalScore() {
        return cardScoreCalculator.calculateTotalScore(cards);
    }

    public void printTotalScore() {
        printAllCards();
        int totalScore = cardScoreCalculator.calculateTotalScore(cards);
        System.out.println(String.format(" - 결과: %d", totalScore));
    }

    public int getCardCount() {
        return cards.size();
    }

    public BlackjackUserResult createBlackjackUserResult() {
        return new BlackjackUserResult(getCardCount(), getTotalScore());
    }
}
