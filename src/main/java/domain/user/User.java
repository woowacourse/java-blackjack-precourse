package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.game.Constant;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public User() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isDead() {
        return !hasPositiveDistance();
    }

    public boolean hasPositiveDistance() {
        return getDistanceToTarget() >= 0;
    }

    public boolean isBlackJack() {
        return hasDistanceEqualTo(0);
    }

    public boolean hasDistanceEqualTo(int minDistance) {
        return getDistanceToTarget() == minDistance;
    }

    public int getDistanceToTarget() {
        return Constant.TARGET.getScore() - getScore();
    }

    public int getScore() {
        int sumOfScores = sumCardScores();
        if (hasAce()) {
            return adjustAceScore(sumOfScores);
        }
        return sumOfScores;
    }

    private int sumCardScores() {
        return this.cards.stream()
                .map(Card::getSymbol)
                .mapToInt(Symbol::getScore)
                .sum();
    }

    public boolean hasAce() {
        return this.cards.stream().anyMatch(Card::isAce);
    }

    private int adjustAceScore(int sumOfScores) {
        int adjustment =  Constant.ALTERNATIVE_ACE.getScore() - Symbol.ACE.getScore();
        if (sumOfScores + adjustment <=  Constant.TARGET.getScore()) {
            return sumOfScores + adjustment;
        }
        return sumOfScores;
    }

    public String getCardInfo() {
        String result =  "";
        for (Card card : this.cards) {
            result += card.getCardInfo();
            result += " ";
        }
        return result;
    }

    public String getCardInfoWithScore() {
        return getCardInfo() + "-결과: " + getScore();
    }
}
