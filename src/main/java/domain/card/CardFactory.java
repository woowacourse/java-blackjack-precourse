package domain.card;

import java.util.*;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {
    private static Symbol[] symbols = Symbol.values();
    private static Type[] types = Type.values();

    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }

    public List<Card> shuffleCard() {
        List<Card> cardList = create();
        Set<Card> shuffledCardList = new LinkedHashSet<>();
        RandomNumberGenerator prng = new RandomNumberGenerator(symbols.length, types.length);
        while (shuffledCardList.size() != symbols.length * types.length) {
            shuffledCardList.add(cardList.get(prng.generate()));
        }
        return (List<Card>) shuffledCardList;
    }

}
