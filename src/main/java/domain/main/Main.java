package domain.main;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static CardFactory cardFactory = new CardFactory();
    private static OutputPrint outputPrint = new OutputPrint();

    public static void main(String[] args) {
        List<Card> cards = new ArrayList<Card>();
        cards = cardFactory.create();
//        System.out.print(cards);
        outputPrint.getPlayerNames();



    }

}
