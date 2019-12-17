package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {
    private static final int ZERO = 0;
    private static final List<Integer> shuffledDeckIndex = new LinkedList<Integer>();
    
    /** 카드갯수만큼 인덱스넘버를 생성하여 섞여진 카드덱을 구성한다. */
    public CardFactory() {
        for (int i = 0; i < create().size(); i++) {
            shuffledDeckIndex.add(i);
        }
        Collections.shuffle(shuffledDeckIndex);
    }
    
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
    
    /** 뽑힌 인덱스넘버에 해당하는 카드를 반환하고, 뽑힌 인덱스넘버는 삭제한다. */
    public static Card shift() {
        Card resultCard = create().get(shuffledDeckIndex.get(ZERO));
        shuffledDeckIndex.remove(ZERO);
        return resultCard;
    }
}
