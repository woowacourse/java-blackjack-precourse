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
    private final Scanner scanGetCardState = new Scanner(System.in);

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(HashMap<Card, Integer> useState, List<Card> cardTrump) {
        Random suffleCard = new Random();
        int chooseCardIndex = suffleCard.nextInt(52);
        while(isUserCard(useState,chooseCardIndex,cardTrump)) {
            chooseCardIndex = suffleCard.nextInt(52);
        }
        cards.add(cardTrump.get(chooseCardIndex));
        useState.put(cardTrump.get(chooseCardIndex),1);
    }

    public int firstBlackJackWinnerMoney(int money) {
        return (int) (money * 1.5);
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
            allScore += card.getScore();
        }
        return allScore;
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
