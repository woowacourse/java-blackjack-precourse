package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public class User {
    private final List<Card> cards = new ArrayList<>();
    private static final Card ace = new Card(Symbol.ACE, null);
    private static final int MAX_SCORE = 21;
    private static final int BIG_ACE_SCORE = 11;
    private static final int SMALL_ACE_SCORE = 1;
    private static final int DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE = BIG_ACE_SCORE - SMALL_ACE_SCORE;

    private int score = 0;
    private boolean isAce = false;
    private boolean isBigAce = false;

    public void addCard(Card card) {
        cards.add(card);
        setScore(card);
    }

    private void setScore(Card card) {
        score += card.getScore();
        if (card.isSymbolEquals(ace)) {
            isAce = true;
        }
        if (score > MAX_SCORE && isBigAce) {
            score -= DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE;
            isBigAce = false;
            return;
        }
        if (score + DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE <= MAX_SCORE && isAce) {
            score += DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE;
            isBigAce = true;
        }
    }

    public List<Card> showCards() {
        return cards;
    }

    public boolean isScoreExceed() {
        System.out.println(score);
        return score >= MAX_SCORE;
    }
}
