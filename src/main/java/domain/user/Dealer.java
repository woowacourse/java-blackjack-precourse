package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.game.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final int DEALER_CRITERIA = 16;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = getCardsPoint();

        if (cardsContainsAce() && ((score+10) <= GameManager.BLACK_JACK)) {
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

    public boolean isWinner(int maxValue) {
        if (getScore() > 21) {
            return false;
        }
        if (getScore() >= maxValue) {
            return true;
        }
        return false;
    }

    public boolean getMoreDraw() {
        if (getScore() <= DEALER_CRITERIA) {
            return true;
        }
        return false;
    }

    public Card getSmallestCard() {
        Card smallestCard = cards.get(0);
        int smallestScore = smallestCard.getSymbolValue();

        for (Card card : cards) {
            if (card.getSymbolValue() < smallestScore) {
                smallestScore = card.getSymbolValue();
                smallestCard = card;
            }
        }
        return smallestCard;
    }

    public int getEarnMoney(ArrayList<Player> players, int maxValue) {
        double dealerMoney = 0;

        for (Player player : players) {
            dealerMoney -= player.getEarnMoney(maxValue);
        }
        return (int) dealerMoney;
    }
}
