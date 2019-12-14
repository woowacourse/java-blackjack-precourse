package domain.user;

import domain.card.Card;

import java.util.List;

/**
 * 딜러와 플레이어를 포괄하는 객체
 */
abstract public class Participant {
    String name;
    List<Card> cards;

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
            if (cards.get(i).getCardScore() == -1)
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
            addScore += 1;
        }
        return addScore;
    }
}
