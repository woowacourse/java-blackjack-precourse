package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class BlackJackPlayer {
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
     * 플레이어의 패가 블랙잭이라면 winners List에 추가하는 메소드
     *
     * @param winners 승자 리스트
     * @return 블랙잭이면 true, 아니면 false
     */
    public Boolean addToWinnerIfBlackJack(List<BlackJackPlayer> winners) {
        if (getSumOfCards() == BLACK_JACK_NUMBER) {
            winners.add(this);
            return true;
        }
        return false;
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
