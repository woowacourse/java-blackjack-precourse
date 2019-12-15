package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return cards;
    }
    
    public static List<Card> cardShuffled() {
    	List<Card> cardSet = CardFactory.create();
    	Collections.shuffle(cardSet);
    	return cardSet;
    }
    
    public static Card pickOneCard(List<Card> cards) {
    	Card target = cards.get(cards.size()-1);
    	cards.remove(cards.size()-1);
    	return target;
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
