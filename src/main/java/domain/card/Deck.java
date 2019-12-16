package domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @since 2019-12-16
 * @author KIMSIYOUNG
 * @apiNote 블랙잭 게임을 위해 카드팩토리 클래스에서 생성된 덱을 제공, 관리 하는 기능을 제공하며
 * @apiNote 한 경기에, 하나의 덱만이 사용되기에 싱글톤으로 작성하였습니다.
 */
public class Deck {
    private static final int DECK_COUNT = 51;

    private static Deck deck;
    private List<Card> cards = CardFactory.create();
    private Set<Integer> setNotForDuplicationOfIndex = new HashSet<>();

    public static Deck getInstance() {
        if (deck == null) {
            deck = new Deck();
        }
        return deck;
    }

    public Card getRandomCard() {
        return cards.get(makeRandomIndex());
    }

    /**
     * 중복된 카드사용을 제거하기 위해, Set에 index를 담아 검증하는 메서드입니다.
     * @return 중복되지 않은(이미 나누어지지 않은) randomNumber
     */
    private int makeRandomIndex() {
        int before = setNotForDuplicationOfIndex.size();
        int randomNumber = (int) (Math.random() * DECK_COUNT);
        setNotForDuplicationOfIndex.add(randomNumber);
        int after = setNotForDuplicationOfIndex.size();
        if (before == after)
            return makeRandomIndex();
        return randomNumber;
    }

}
