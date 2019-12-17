package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Card> hisCard = new ArrayList<>();
    protected int balance;

    public void addCard(Card card) {
        hisCard.add(card);
    }

    public void addCard(List<Card> cardList) {
        hisCard.addAll(cardList);
    }

    public boolean isLose() {
        final int boundary = 21;
        if (calcScore() > boundary)
            return true;
        return false;
    }

    public boolean isBlackJack() {
        final int boundary = 21;
        if (calcScore() == boundary)
            return true;
        return false;
    }

    private int calcScore() {
        int score = 0;
        for (Card it : hisCard)
            score += it.getScore();
        return score;
    }


}
