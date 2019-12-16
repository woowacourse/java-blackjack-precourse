package domain.user;

import domain.Score;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private double profit = 0;
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    /**
     * 필드에 있는 cards의 점수 총합 계산
     */
    public int cardSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getSymbol().getScore();
        }
        for (Card card : cards) {
            sum += checkAceExists(card, sum);
        }
        return sum;
    }

    /**
     * ACE카드의 총합을 11에서 1로 바꾼다.
     */
    private int checkAceExists(Card card, int sum) {
        if (card.isAce() && (sum > Score.BLACK_JACK)) {
            return -10;
        }
        return 0;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }

    public String print() {
        return "딜러: " + printCardList();
    }

    /**
     * 필드에 있는 cards 안에 있는 카드들을 출력한다.
     */
    protected String printCardList() {
        List<String> cardList = new ArrayList<>();
        for (Card card : cards)
            cardList.add(card.printCard());
        return String.join(", ", cardList);
    }
}
