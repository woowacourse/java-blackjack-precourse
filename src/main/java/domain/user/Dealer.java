package domain.user;

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
    public int cardSum() {
        int sum = 0;
        for (int i = 0 ; i < cards.size() ; i++) {
            sum += cards.get(i).getSymbol().getScore();
        }
        return sum;
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

    protected String printCardList() {
        List<String> cardList = new ArrayList<>();
        for (Card card : cards)
            cardList.add(card.getCard());
        return String.join(", ", cardList);
    }
}
