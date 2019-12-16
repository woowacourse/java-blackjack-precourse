package domain.scoring;

import java.util.List;
import domain.card.Card;

public class ScoreCalculator {
    final static int BLACKJACK_MAX_SCORE = 21;
    final static int ACE_LARGE_SCORE = 11;
    final static int ACE_SMALL_SCORE = 1;

    public static int getTotalScore(List<Card> cards) {
        return getOptimizedScoreWithAces(getTotalScoreWithoutAce(cards), countAce(cards));
    }

    private static int getTotalScoreWithoutAce(List<Card> cards) {
        int totalScoreWithoutAce = 0;

        for (Card card : cards) {
            totalScoreWithoutAce += card.getScoreWithoutAce();
        }

        return totalScoreWithoutAce;
    }

    private static int countAce(List<Card> cards) {
        int aceCount = 0;

        for (Card card : cards) {
            aceCount += returnNumber1IfAce(card);
        }

        return aceCount;
    }

    private static int returnNumber1IfAce(Card card) {

        if (card.isAce()) {
            return 1;
        }

        return 0;
    }

    private static int getOptimizedScoreWithAces(int scoreWithoutAce, int aceCount) {
        int score = scoreWithoutAce + (aceCount * ACE_SMALL_SCORE);    /* 모든 ACE를 ACE_SMALL_SCORE 로 계산한 경우 */

        for (int aceCountAsLarge = 1; aceCountAsLarge <= aceCount; aceCountAsLarge++) {
            /*
             * aceCountAsLarge : score 가 ACE_LARGE_SCORE 로 계산되는 ACE 카드의 갯수
             * anotherCaseScoreUsingAces : 기존 score 와 다른 방식으로 ACE 를 계산한 경우의 점수
             */
            int anotherCaseScoreUsingAces = scoreWithoutAce
                            + (aceCountAsLarge * ACE_LARGE_SCORE)
                            + (aceCount - aceCountAsLarge) * ACE_SMALL_SCORE;
            score = getMoreReasonableScore(score, anotherCaseScoreUsingAces);
        }

        return score;
    }

    private static int getMoreReasonableScore(int score1, int score2) {
        int largerScore = chooseLargerScore(score1, score2);
        int smallerScore = chooseSmallerScore(score1, score2);

        if (largerScore < BLACKJACK_MAX_SCORE) {
            return largerScore;
        }

        return smallerScore;
    }

    private static int chooseLargerScore(int score1, int score2) {

        if (score1 < score2) {
            return score2;
        }

        return score1;
    }

    private static int chooseSmallerScore(int score1, int score2) {

        if (score1 < score2) {
            return score1;
        }

        return score2;
    }
}
