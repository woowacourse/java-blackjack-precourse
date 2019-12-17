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
        return this.getSumScore() == 21;
    }

    public boolean isHitDealerCard() {
        return this.sumWhenAisEleven() < 17;
    }

    public int getSumScore() {
        if (this.isWhenAisElevenBust()) {
            return this.sumWhenAisOne();
        }
        return this.sumWhenAisEleven();
    }

    public boolean isSumScoreBust() {
        return this.getSumScore() > 21;
    }

    private int sumWhenAisEleven() {
        int score = 0;
        for (Card card: this.cards) {
            score += checkOneOrEleven(card.getSymbolScore());
        }
        return score;
    }

    private int checkOneOrEleven(int score) {
        if (score == 1) {
            return 11;
        }
        return score;
    }

    private int sumWhenAisOne() {
        int score = 0;
        for (Card card: this.cards) {
            score += card.getSymbolScore();
        }
        return score;
    }

    public boolean isWhenAisElevenBust() {
        return this.sumWhenAisEleven() > 21;
    }

    public boolean isAbleHitPlayer() {
        return this.sumWhenAisOne() < 21 && !this.isBlackJack();
    }
}
