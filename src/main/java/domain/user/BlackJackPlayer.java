package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class BlackJackPlayer {
    public static final int BURST_SCORE = 0;

    public static final int BLACK_JACK_NUMBER = 21;

    public static final int ACE_BONUS = 10;

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

    /**
     * @return 패의 합이 21이하면 패의 합, 21 초과면 0
     */
    public int getScoreOfCards() {
        int sumOfCards = getSumOfCards();

        if (sumOfCards > BLACK_JACK_NUMBER) {
            return 0;
        }
        return sumOfCards;
    }

    /**
     * 현재 패가 블랙잭인지 확인하는 메소드
     */
    public Boolean ifHaveBlackJack() {
        return getSumOfCards() == BLACK_JACK_NUMBER;
    }

    public String getCardsName() {
        return cards.toString();
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
