package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = getCardsPoint();

        if (cardsContainsAce() && (score+10) <= 21) {
            score += 10;
        }
        return score;
    }

    public boolean cardsContainsAce() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    private int getCardsPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getSymbolValue();
        }
        return point;
    }

    public String getCardListString() {
        List<String> cardStringList = new ArrayList<>();

        for (Card card : cards) {
            cardStringList.add(card.toPrintString());
        }
        return String.join(", ", cardStringList);
    }

//    public boolean completeBlackJack() {
//        if (getCardsPoint() == 21) {
//            return true;
//        }
//        if ((getCardsPoint() == 11) && cardsContainsAce()) {
//            return true;
//        }
//        return false;
//    }

    public boolean isWinner(int maxValue) {
        if (getScore() > 21) {
            return false;
        }
        if (getScore() >= maxValue) {
            return true;
        }
        return false;
    }

    public double getEarnMoney(int maxValue, double battingRatio) {
        if (isWinner(maxValue)) {
            return bettingMoney * battingRatio;
        }
        return (-1) * bettingMoney * battingRatio;
    }

    public String getName() {
        return name;
    }
}
