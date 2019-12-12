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

    public String getCardsName() {
        return cards.toString();
    }

    private int getSumOfCards() {
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
     * 기본 스코어를 다 더한 다음, Ace의 갯수만큼 10을 더해줄지 말지 결정하는 메소드
     *
     * @param totalScore 기본 스코어(Ace를 1로 계산)
     * @param countAce Ace가 나타난 숫자
     * @return Ace의 추가 점수가 계산된 스코어
     */
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
