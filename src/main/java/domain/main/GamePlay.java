package domain.main;

import domain.card.Card;
import domain.card.CardFactory;
import java.util.ArrayList;
import java.util.List;

public class GamePlay {
    public static final int NUMBER_OF_CARDS = 52;
    public static List<Card> cardList = CardFactory.create();
    public static List<Integer> usedCardIndexList = new ArrayList<Integer>();
    public static int drawCardIndex;

    public static int getNewCardIndex() {
        return (int) (Math.random() * NUMBER_OF_CARDS);
    }

    public static boolean checkNewCardIndex(){
        return usedCardIndexList.contains(drawCardIndex);
    }

    public static int makeNewCardIndex() {
        do {
            drawCardIndex = getNewCardIndex();
        } while (checkNewCardIndex());
        usedCardIndexList.add(drawCardIndex);
        return drawCardIndex;
    }

    public static Card addNewCard() {
        return cardList.get(makeNewCardIndex());
    }

}
