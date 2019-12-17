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

    public String userCardsInfo(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cards.size() - 1; i++) {
            stringBuilder.append(cards.get(i).toString() + ", ");
        }
        stringBuilder.append(cards.get(cards.size() - 1));
        return stringBuilder.toString();
    }

    public int getSumNumbers() {
        return sumNumbers;
    }

    public void addSumNumbers(int sumNumbers) {
        this.sumNumbers += sumNumbers;
    }

    public double getProfit() {
        return profit;
    }

    public void addProfit(double profit) {
        this.profit += profit;
    }
}
