package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class User {

    private static final int ANOTHER_ACE_NUMBER = 11;
    private static final int TRUMP_CARD_SIZE = 52;
    private static final int PICKED_CARD_STATE = 1;
    private static final int BLACKJACK = 21;
    private static final int ACE_NUMBER = 1;

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCard() {
        return this.cards;
    }

    public List<Card> addCard(HashMap<Card, Integer> useState, List<Card> cardTrump) {
        Random suffleCard = new Random();
        int chooseCardIndex = suffleCard.nextInt(TRUMP_CARD_SIZE);
        while(isUserCard(useState,chooseCardIndex,cardTrump)) {
            chooseCardIndex = suffleCard.nextInt(TRUMP_CARD_SIZE);
        }
        cards.add(cardTrump.get(chooseCardIndex));
        useState.put(cardTrump.get(chooseCardIndex),PICKED_CARD_STATE);
        return cards;
    }

    private boolean isUserCard(HashMap<Card, Integer> useState, int cardIndex, List<Card> cardTrump) {
        if (isSameCard(cardTrump.get(cardIndex))) {
            return true;
        }
        return useState.containsKey(cardTrump.get(cardIndex));
    }

    public boolean isSameCard(Card card) {
        for(Card playerCard : cards) {
            if(playerCard.equals(card)) {
                return true;
            }
        }
        return false;
    }

    private int anotherCardsSum(List<Card> cards, Card card, int score, int cardIndex) {
        if(!cards.get(cardIndex).equals(card)) {
            score += cards.get(cardIndex).getScore();
        }
        return score;
    }

    public int allScore() {
        int allScores = 0;
        for(Card card : cards) {
            if(card.getSymbol() == Symbol.ACE){
                allScores += aceValue(cards, card);
                continue;
            }
            allScores += card.getScore();
        }
        return allScores;
    }

    private int aceValue(List<Card> cards, Card card) {
        int score = 0;
        for(int i=0; i < cards.size(); i++) {
            score = anotherCardsSum(cards, card, score, i);
        }
        if(score + ANOTHER_ACE_NUMBER <= BLACKJACK) {
            System.out.println("11");
            return ANOTHER_ACE_NUMBER;
        }
        System.out.println("1");
        return ACE_NUMBER;
    }

    public void displayCardState() {
        for(Card card : cards) {
            System.out.print(card.toString() + " ");
        }
        System.out.println();
    }

}
