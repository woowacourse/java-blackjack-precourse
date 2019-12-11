package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class BlackJackPlayer {
    public static final int BLACK_JACK_NUMBER = 21;

    public static final int ACE_BONUS = 10;

    private final List<Card> cards = new ArrayList<>();

    public abstract String getName();

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
     * @param winningScore 승자의 점수 (패의 합)
     * @return 플레이어의 패의 합이 승리한 사람의 점수와 같다면 true, 아니면 false
     */
    public Boolean ifHaveWinnerScore(int winningScore) {
        if (getSumOfCards() == winningScore) {
            return true;
        }
        return false;
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
