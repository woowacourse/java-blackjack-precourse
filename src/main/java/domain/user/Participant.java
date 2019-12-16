package domain.user;

import domain.card.Card;

import java.util.List;

/**
 * 딜러와 플레이어를 포괄하는 객체
 */
abstract public class Participant {
    String name;
    List<Card> cards;
    private double profit = 0;

    protected Participant(String name) {
        this.name = name;
    }

    abstract public void addCard(Card card);

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        if (index >= cards.size())
            return null;
        return cards.get(index);
    }

    public int getSumScore() {
        int sumScore = 0;
        int aceCount = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardScore() == 1)
                aceCount++;
            sumScore += cards.get(i).getCardScore();
        }
        return sumScore + addAce(sumScore, aceCount);
    }

    // ace가 11일 때 21을 초과하지 않는다면 11로, 그렇지않다면 1로 값을 정한다.
    public int addAce(int sumScore, int aceCount) {
        int addScore = 0;
        while (aceCount-- > 0) {
            System.out.println("반복!");
            if (sumScore + addScore + 11 < 21) {
                addScore += 10;
            }
        }
        return addScore;
    }

    public boolean isDealer() {
        if (this.name == "딜러") {
            return true;
        }

        if (this.name.equals("딜러")) {
            return true;
        }
        return false;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setProfitByMultiple(double multi) {
        if (this.isDealer())
            return;
        this.profit = (((Player) this).getBettingMoney()) * multi;
    }

    public double getProfit() {
        return this.profit;
    }
}
