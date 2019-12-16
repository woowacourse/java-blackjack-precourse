package domain.main;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Card> aa = CardFactory.create();
        Card bb = new Card(Symbol.ACE, Type.SPADE);
        System.out.println(aa.indexOf(bb));
        System.out.println(aa.size());
        List<Integer> used_card = new ArrayList<Integer>();

        int drawCard = (int) (Math.random() * 52);

        if (!used_card.contains(drawCard)) {
            used_card.add(drawCard);
        }
    }
}
