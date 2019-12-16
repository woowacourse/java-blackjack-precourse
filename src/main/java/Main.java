import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Card> cards = CardFactory.provideShuffledCards();

        for (Card card: cards) {
            System.out.println(card.toString());
        }
    }
}
