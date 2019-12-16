package domain.user;

import domain.card.Card;
import domain.function.CardScoreCalculator;

import java.util.ArrayList;
import java.util.List;

public class BlackjackUser {
    protected final List<Card> cards = new ArrayList<>();
    protected final String name;
    private final CardScoreCalculator cardScoreCalculator = new CardScoreCalculator();

    public BlackjackUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getAllCardNames() {
        List<String> allCardNames = new ArrayList<>();
        for (Card card : cards) {
            allCardNames.add(card.getName());
        }
        return String.join(", ", allCardNames);
    }

    public int getTotalScore() {
        return cardScoreCalculator.calculateTotalScore(cards);
    }

    public int getCardCount() {
        return cards.size();
    }

    public BlackjackUserResult createBlackjackUserResult() {
        return new BlackjackUserResult(getCardCount(), getTotalScore());
    }
}
