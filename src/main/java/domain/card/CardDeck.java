package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 카드 덱을 관리하는 객체
 */
public class CardDeck {
    private static final int FIRST = 0;

    private List<Card> cardDeck;

    /**
     * 카드 뽑는 순서를 정해주는 인스턴스 변수
     */
    private List<Integer> drawOrder;

    public CardDeck(List<Card> cards) {
        cardDeck = cards;
        drawOrder = makeRandomDrawOrder();
    }

    /**
     * @return 덱에서 뽑은 랜덤한 Card 객체
     */
    public Card drawCard() {
        Card card = cardDeck.get(drawOrder.get(FIRST));

        drawOrder.remove(FIRST);        // 뽑은 카드는 뽑는 순서에서 제거
        return card;
    }

    /**
     * @return 0~51의 숫자를 랜덤으로 셔플한 List
     */
    private List<Integer> makeRandomDrawOrder() {
        List<Integer> drawOrder = IntStream.range(FIRST, cardDeck.size()).boxed().collect(Collectors.toList());

        Collections.shuffle(drawOrder, new Random(System.nanoTime()));
        return drawOrder;
    }
}
