package domain.user;

import domain.card.Card;
import game.GameConstants;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;


public abstract class Entry {
    private final List<Card> cards = new ArrayList<>();
    private double profits = 0;

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getOneCardName() {
        return cards.get(0).toString();
    }

    public String getAllCardName() {
        return StringUtil.joinCardName(cards);
    }

    public int getScore() {
        int sum = cards.stream().mapToInt(Card::getScore).sum();
        if (hasAce() && sum <= GameConstants.ACE_TO_TEN_COUNT) {
            sum += GameConstants.ACE_COUNT;
        }
        return sum;
    }

    private boolean hasAce() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    public void addProfits(double profits) {
        this.profits += profits;
    }

    public double getProfits() {
        return profits;
    }
}