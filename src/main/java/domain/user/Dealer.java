package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }
    public void getFirstCards(List<Card> allCards, List<Card> getCardsList) {
        for (int i = 0; i < 2; i++) {
            this.cards.add(this.getRandomCard(allCards, getCardsList));
        }
    }

    public Card getRandomCard(List<Card> allCards, List<Card> getCardsList) {
        Random random = new Random();
        Card getCard = null;
        boolean checkCard = true;
        while (checkCard) {
            getCard = allCards.get(random.nextInt(allCards.size()));
            checkCard = isDoubleCheck(getCardsList, getCard);
        }
        getCardsList.add(getCard);
        return getCard;
    }

    private boolean isDoubleCheck(List<Card> getCardsList, Card getCard) {
        return getCardsList.contains(getCard);
    }

    public boolean isBlackJack() {
        return this.getElevenEqualsAScore() == 21;
    }

    public int getSumScore() {
        int count = 0;
        for (Card card: this.cards) {
            count += card.getSymbolScore();
        }
        return count;
    }

    public boolean getBooleanSumScore() {
        int count = 0;
        for (Card card: this.cards) {
            count += card.getSymbolScore();
        }
        return count < 21;
    }

    public int getSumScoreResult() {
        int oneEqualsA = this.getSumScore();
        int elevenEqualsA = this.getElevenEqualsAScore();
        if (Math.abs(21 - this.getSumScore()) < Math.abs(21 - this.getElevenEqualsAScore())) {
            return oneEqualsA;
        }
        return elevenEqualsA;
    }

    public int getElevenEqualsAScore() {
        int count = 0;
        for (Card card: this.cards) {
            count += checkOneOrEleven(card.getSymbolScore());
        }
        return count;
    }

    private int checkOneOrEleven(int score) {
        if (score == 1) {
            return 11;
        }
        return score;
    }
}
