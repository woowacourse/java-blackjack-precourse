package domain.card;

import java.util.*;

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
        int randomNumber = new Random().nextInt(Symbol.values().length);
        selectSymbol = randomSymbolSelect(randomNumber);
        return selectSymbol;
    }

    private static Symbol randomSymbolSelect(int randomNumber) {
        Set<Object> randomSet = new HashSet<Object>();
        int loopTempNumber = 0;
        for (Symbol symbol : Symbol.values()) {
            randomSet.add(enumRandomSelect(symbol, randomNumber, loopTempNumber));
            loopTempNumber++;
        }
        randomSet.remove(0);
        Symbol symbol = (Symbol) new ArrayList(randomSet).get(0);
        return symbol;
    }

    private static Type randomType() {
        Type selectType = null;
        int randomNumber = new Random().nextInt(Type.values().length);
        selectType = randomTypeSelect(randomNumber);
        return selectType;
    }

    private static Type randomTypeSelect(int randomNumber) {
        Set<Object> randomSet = new HashSet<Object>();
        int loopTempNumber = 0;
        for (Type type : Type.values()) {
            randomSet.add(enumRandomSelect(type, randomNumber, loopTempNumber));
            loopTempNumber++;
        }
        randomSet.remove(0);
        Type type = (Type) new ArrayList(randomSet).get(0);
        return type;
    }

    private static Object enumRandomSelect(Object object, int randomNumber, int loopTempNumber) {
        if (randomNumber == loopTempNumber) {
            return object;
        }
        return 0;
    }
}
