package domain.user;

import domain.card.Card;

import java.util.*;

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

    // TODO 추가 기능 구현
    public List<Card> drawCards(int cardNumber, List<Card> cardDeck) {
        for (int i = 0; i < cardNumber; i++) {
            addCard(cardDeck.get(0));
            cardDeck.remove(0);
        }
        return cardDeck;
    }

    public int cardScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            score += card.getCardScore();
            aceCount += card.isAce();
        }

        if (score > 21) {
            score = checkBurst(score, aceCount);
        }
        return score;
    }

    public int checkBurst(int score, int aceCount) {
        while (score <= 21 || aceCount != 0) {
            aceCount--;
            score -= 9;
        }
        return score;
    }

    public void showCards() {
        System.out.println(this.name + "카드 : " + cards.get(0).getCardName() + ", " + cards.get(1).getCardName());
    }

    public void showFinal() {
        System.out.println(this.name + "카드 : " + cards.get(0).getCardName() + ", " + cards.get(1).getCardName()
                + " - 결과:" + cardScore());
    }

    public String getName() {
        return this.name;
    }

    public double getMoney() {
        return this.bettingMoney;
    }

    public String winner() {
        return this.name + ": " + (int)bettingMoney + "\n";
    }

    public String loser() {
        return this.name + ": -" + (int)bettingMoney + "\n";
    }
}
