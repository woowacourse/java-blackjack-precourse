package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

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

    public int sumUpCards(){
        int sumOfCards = 0;
        for(Card each: cards){
            sumOfCards += each.getNumber();
        }
        return sumOfCards;
    }

    public void printNameAndCards(){
        List<String> cardNames = new ArrayList<>();
        for( Card card : this.cards){
            cardNames.add(card.toString());
        }
        System.out.print("딜러: ");
        System.out.println(String.join(",", cardNames));
    }
}
