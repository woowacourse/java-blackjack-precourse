package domain.main;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Card> cards;
        CardFactory factory = new CardFactory();
        cards = factory.create();

        for(Card card : cards){
            System.out.println(card.toString());
        }
        System.out.println(cards.size());
        Manager manager = new Manager(cards);
        manager.start();
    }
}
