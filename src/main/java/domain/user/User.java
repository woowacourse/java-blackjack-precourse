package domain.user;

import java.util.List;
import domain.card.Card;

public abstract class User {
    int sumNumbers = 0;
    double profit = 0.0;

    public abstract void addCard(Card card);

    public boolean stopGameYN(int sumNumbers) {
        if (sumNumbers >= 21) {
            return true;
        }
        return false;
    }

    public void userCardsInfo(List<Card> cards, String name) {
        System.out.print(name + "카드 : ");
        for (int i = 0; i < cards.size() - 1; i++) {
            System.out.print(cards.get(i).toString() + ", ");
        }
        System.out.println(cards.get(cards.size() - 1).toString());
    }

    public int getSumNumbers() {
        return sumNumbers;
    }

    public void setSumNumbers(int sumNumbers) {
        this.sumNumbers = sumNumbers;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit += profit;
    }
}
