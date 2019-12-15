package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.*;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player{

    private static final int ANOTHER_ACE_NUMBER = 11;
    private static final int ACE_NUMBER = 1;
    private static final int TRUMP_CARD_SIZE = 52;
    private static final int PICKED_CARD_STATE = 1;
    private static final double FIRST_TRUN_BLACKJACK_WIN_BETTING_RATE = 1.5;

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();


    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
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

    public int firstBlackJackWinnerMoney(int money) {
        return (int) (money * FIRST_TRUN_BLACKJACK_WIN_BETTING_RATE);
    }

    public String getName() {
        return this.name;
    }

    public double getBettingMoney() {
        return this.bettingMoney;
    }

    public List<Card> getCard() {
        return this.cards;
    }

    public boolean isSameCard(Card card) {
        for(Card playerCard : cards) {
            if(playerCard.equals(card)) {
                return true;
            }
        }
        return false;
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

    private int chooseAceOneOrEleven(List<Card> cards, Card card) {
        int score = 0;
        for(int i = 0; i < cards.size(); i++) {
            score = anotherCardsSum(cards, card, score, i);
        }
        if(score + ANOTHER_ACE_NUMBER <= TRUMP_CARD_SIZE) {
            return ANOTHER_ACE_NUMBER;
        }
        return ACE_NUMBER;
    }

    private int anotherCardsSum(List<Card> cards, Card card, int score, int cardIndex) {
        if(!cards.get(cardIndex).equals(card)) {
            score += cards.get(cardIndex).getScore();
        }
        return score;
    }

    private boolean isUserCard(HashMap<Card, Integer> useState, int cardIndex, List<Card> cardTrump) {
        if (isSameCard(cardTrump.get(cardIndex))) {
            return true;
        }
        return useState.containsKey(cardTrump.get(cardIndex));
    }

    public void displayCardState() {
        for(Card card : cards) {
            System.out.print(card.toString() + " ");
        }
        System.out.println();
    }

    // TODO 추가 기능 구현

}
