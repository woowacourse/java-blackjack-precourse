package domain.user;

import domain.card.Card;
import domain.game.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {

    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int MINUS = -1;
    private static final double BASIC_BATTING_RATIO = 1.0;
    private static final double BIGGER_BATTING_RATIO = 1.5;

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

        if (cardsContainsAce() && ((score + ACE_ADDITIONAL_SCORE) <= GameManager.BLACK_JACK)) {
            score += ACE_ADDITIONAL_SCORE;
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

    public boolean isWinner(int maxValue) {
        if (getScore() > GameManager.BLACK_JACK) {
            return false;
        }
        if (getScore() >= maxValue) {
            return true;
        }
        return false;
    }

    public int getEarnMoney(int maxValue) {
        double battingRatio = BASIC_BATTING_RATIO;

        if ((cards.size() == GameManager.START_CARD_COUNT) && (getScore() == GameManager.BLACK_JACK)) {
            battingRatio = BIGGER_BATTING_RATIO;
        }
        if (isWinner(maxValue)) {
            return (int) (bettingMoney * battingRatio);
        }
        return (int) (MINUS * bettingMoney * battingRatio);
    }

    public String getName() {
        return name;
    }
}
