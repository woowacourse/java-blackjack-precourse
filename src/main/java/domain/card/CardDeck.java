package domain.card;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
    private static List<Card> cardDeck = new ArrayList<Card>();

    private CardDeck() {
        cardDeck = CardFactory.create();
    }

    public static Card drawACard() {
        Symbol symbol = randomSymbol();
        Type type = randomType();
        Card card = new Card(symbol, type);
        cardDeck.remove(card);
        return card;
    }

    private static Symbol randomSymbol() {
        Symbol selectSymbol = null;
        int randomNumber = new Random().nextInt(Symbol.values().length + 1);
        int loopTempNumber = 0;
        for (Symbol symbol : Symbol.values()) {
            selectSymbol = (Symbol) enumRandomSelect(symbol, randomNumber, loopTempNumber);
            loopTempNumber++;
        }
        return selectSymbol;
    }

    private static Type randomType() {
        Type selectType = null;
        int randomNumber = new Random().nextInt(Type.values().length + 1);
        int loopTempNumber = 0;
        for (Type type : Type.values()) {
            selectType = (Type) enumRandomSelect(type, randomNumber, loopTempNumber);
            loopTempNumber++;
        }
        return selectType;
    }

    private static Object enumRandomSelect(Object object, int randomNumber, int loopTempNumber) {
        if (randomNumber == loopTempNumber) {
            return object;
        }
        return null;
    }
}
