package domain.main;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;

import java.util.ArrayList;
import java.util.List;

public class GamePlay {
    private static final int NUMBER_OF_CARDS = 52;

    public static List<Card> cardList = CardFactory.create();
    public List<Integer> usedCardsIndex = new ArrayList<Integer>();
    public static int drawCardIndex;

    public static int getNewCardIndex() {
        return (int) (Math.random() * NUMBER_OF_CARDS);
    }

    public static boolean checkNewCardIndex(List<Integer> usedCardsIndex, int drawCardsIndex) {
        return usedCardsIndex.contains(drawCardsIndex);
    }

    public static void addNewCardIndex(List<Integer> usedCardsIndex) {

    }

    public static void addNewCardForUser(int drawCardIndex){

    }

}
