package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    public List<Card> generateCards(int numOfCards, List<Card> allCards){
        for(int i = 0; i < numOfCards; i++){
            Collections.shuffle(allCards);
            addCard(allCards.get(0));
            allCards.remove(0);
        }
        return allCards;
    }

    public String toString(){
    }

}
