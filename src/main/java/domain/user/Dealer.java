package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer  {
    private static final int ANOTHER_ACE_NUMBER = 11;
    private static final int BLACKJACK = 21;
    private static final int ACE_NUMBER = 1;
    private static final int TRUMP_CARD_SIZE = 52;
    private static final int PICKED_CARD_STATE = 1;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public List<Card> getCard() {
        return this.cards;
    }

    public void addCard(HashMap<Card, Integer> useState, List<Card> cardTrump) {
        Random suffleCard = new Random();
        int chooseCardIndex = suffleCard.nextInt(TRUMP_CARD_SIZE);
        while(isUserCard(useState,chooseCardIndex,cardTrump)) {
            chooseCardIndex = suffleCard.nextInt(TRUMP_CARD_SIZE);
        }
        cards.add(cardTrump.get(chooseCardIndex));
        useState.put(cardTrump.get(chooseCardIndex),PICKED_CARD_STATE);
    }
    private int chooseAceOneOrEleven(List<Card> cards, Card card) {
        int score = 0;
        for(int i=0; i < cards.size(); i++) {
            score = anotherCardsSum(cards, card, score, i);
        }
        if(score + ANOTHER_ACE_NUMBER > BLACKJACK) {
            return ACE_NUMBER;
        }
        return ANOTHER_ACE_NUMBER;
    }

    private int anotherCardsSum(List<Card> cards, Card card, int score, int i) {
        if(!cards.get(i).equals(card)) {
            score += cards.get(i).getScore();
        }
        return score;
    }
    public boolean isSameCard(Card card) {
        for(Card playerCard : cards) {
            if(playerCard.equals(card)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUserCard(HashMap<Card, Integer> useState, int cardIndex, List<Card> cardTrump) {
        if (isSameCard(cardTrump.get(cardIndex))) {
            return true;
        }
        return useState.containsKey(cardTrump.get(cardIndex));
    }
    public int allScore() {
        int allScore = 0;
        for(Card card : cards) {
            if(card.getSymbol() == Symbol.ACE){
                allScore += chooseAceOneOrEleven(cards, card);
                continue;
            }
            allScore += card.getScore();
        }
        return allScore;
    }

    // TODO 추가 기능 구현
}
