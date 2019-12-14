package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GameMc Mc = new GameMc();
        Mc.gameMc();
        Mc.gameStart();
        Mc.isBlackJack();

        Mc.endGame();

//        List<Card> card = CardFactory.create();
//        ArrayList<Card> deck = new ArrayList<Card>();
//        deck.add(card.get(1));
//        deck.add(card.get(30));
//        System.out.println(deck);
//
//        System.out.println(deck.toString().contains("A"));


    }
}
