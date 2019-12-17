package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.game.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean hasAce() {
        return this.cards.stream()
                .anyMatch(Card::isAce);
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

    private int adjustAceScore(int sumOfScores) {
        int adjustment =  Constant.ALTERNATIVE_ACE.getScore() - Symbol.ACE.getScore();
        if (sumOfScores + adjustment <=  Constant.TARGET.getScore()) {
            return sumOfScores + adjustment;
        }
        return sumOfScores;
    }

    public String getCardInfo() {
        String result =  "딜러의 카드: ";
        for (Card card : this.cards) {
            result += card.getCardInfo();
            result += " ";
        }
        return result;
    }
}
