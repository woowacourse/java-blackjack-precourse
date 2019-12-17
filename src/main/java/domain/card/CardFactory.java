package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 */
public class CardFactory {
	private List<Card> cardDeck = new ArrayList<Card>();
	private static int sizeOfDeck = 0;
	public CardFactory() {
		cardDeck = create();
	}
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards); //shuffle card list at creation
        sizeOfDeck = cards.size()-1;
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
    public List<Card> getCardDeck(){
    	return cardDeck;
    }
    public Card drawCard() {
    	Card card;
    	card = cardDeck.get(sizeOfDeck);
    	sizeOfDeck--;
    	return card;
    }
}
