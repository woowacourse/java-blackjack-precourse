package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CardFactory는 트럼프 카드 전체 생성을 담당하는 객체이다.
 * static으로 구현되어 있으며, 생성 없이 기능을 쓴다는 가정 하에 운용된다.
 *
 * @author kafka
 * @version 1.0
 */
public class CardFactory {
    /**
     * 생성자 메서드는 호출되지 않음을 가정한다.
     * 호출될 때를 대비해, 예외처리를 해 주었다.
     *
     * @throws AssertionError 만약 호출될 경우, 잘못된 선언으로 간주한다.
     */
    private CardFactory() {
        throw new AssertionError();
    }

    /**
     * create는 정적으로 선언된 메서드이며, 카드 한 번을 만든다.
     * 만들어진 카드는 수정 불가한 리스트로 반환된다.
     * 반환 전, 셔플을 해 줌으로써 카드를 쓰는 상위 객체에서 인덱스를 옮기는 것으로
     * 중복 없는 카드 뽑기를 가능하도록 하였다.
     *
     * @return 수정 불가한 카드 1벌(52장)의 리스트
     */
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    /**
     * createByType은 카드를 생성하는 로직 내부의 반복문을 메서드로 옮긴 것이다.
     * 카드 리스트와 심볼이 주어지면, 이를 바탕으로 그 심볼과 매칭되는 모든 타입의 카드를 생성하여 리스트에 삽입한다.
     *
     * @param cards  카드를 저장하는 리스트이다.
     * @param symbol 현재 카드가 가지는 심볼 값이다. 이를 바탕으로 타입을 추가한다.
     *               (심볼 : 1이라면, 여기에 '스페이드'를 붙인다고 예를 들 수 있다)
     */
    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
