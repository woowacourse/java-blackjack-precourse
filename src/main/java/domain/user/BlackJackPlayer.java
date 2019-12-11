package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class BlackJackPlayer {
    private static final int BLACK_JACK_NUMBER = 21;

    private static final int ACE_BONUS = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getSumOfCards() {
        int totalScore = 0;
        int countAce = 0;

        for (Card card : cards) {
            totalScore += card.getScore();
            countAce += card.ifAce();
        }
        totalScore = reviseAcesScore(totalScore, countAce);
        return totalScore;
    }

    private int reviseAcesScore(int totalScore, int countAce) {
        for (int i = 0; i < countAce; i++) {
            totalScore = reviseAceScore(totalScore);
        }
        return totalScore;
    }

    private int reviseAceScore(int totalScore) {
        /* 1 또는 11 중에 최대한 21에 가깝게 되는 숫자를 선택 */

        if (BLACK_JACK_NUMBER - (totalScore + ACE_BONUS) >= 0) {
            return totalScore + ACE_BONUS;
        }
        return totalScore;
    }
}
