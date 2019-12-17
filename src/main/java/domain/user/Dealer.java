package domain.user;

import domain.card.Card;

import java.util.*;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
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

    public void showCards() {
        System.out.println("딜러 : " + cards.get(0).getCardName() + ", " + cards.get(1).getCardName());
    }

    public void showFinal() {
        String cardList = new String();
        for(Card card : cards) {
            cardList += card.getCardName() + ", ";
        }
        System.out.println("딜러 : " + cardList + " - 결과:" + cardScore());
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
            score -= 10;
        }
        return score;
    }

    public boolean checkBlackjack() {
        if(cardScore() == 21) {
            return true;
        }
        return false;
    }
}
