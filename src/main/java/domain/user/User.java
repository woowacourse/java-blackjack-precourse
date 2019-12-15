package domain.user;

import java.util.List;
import domain.card.Card;


public abstract class User {
    int profit = 0;
    int sumNumbers = 0;
    private boolean blackJackTF = false;
    private boolean bustTF = false;
    private boolean twentyoneTF = false;

    abstract void addCard(Card card);

    public boolean blackJackYN(int sumNumbers) {
        if (sumNumbers == 21) {
            blackJackTF = true;
            return true;
        }
        return false;
    }

    public boolean bustYN(int sumNumbers) {
        if (sumNumbers > 21) {
            bustTF = true;
            return true;
        }
        return false;
    }

    public boolean twentyoneYN(int sumNumbers) {
        if (sumNumbers == 21) {
            twentyoneTF = true;
            return true;
        }
        return false;
    }

    public void userCardsInfo(List<Card> cards, String name) {
        System.out.print(name + "카드 : ");
        for (int i = 0; i < cards.size() - 1; i++) {
            System.out.print(cards.get(i).cardInfo() + ", ");
        }
        System.out.println(cards.get(cards.size() - 1).cardInfo());
    }

    public int getSumNumber() {
        return sumNumbers;
    }

    void userSumNumbers() {
        System.out.println(" - 결과 : " + sumNumbers);
    }

    void userProfit(String name) {
        System.out.println(name + " : " + profit);
    }

}
