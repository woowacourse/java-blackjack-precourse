package domain.card;

import java.util.Objects;

import static domain.card.Symbol.ACE;

/**
 * Card 클래스는 카드 한장에 대한 정보를 저장한다.
 *
 * @author kafka
 * @version 1.1
 */
public class Card {
    /**
     * symbol은 카드의 분류 번호값(1~10, 킹/퀸/잭)을 저장한다. 할당 이후 수정될 수 없다.
     */
    private final Symbol symbol;

    /**
     * type은 카드의 분류 타입값(스페이드/다이아몬드/하트/클럽)을 저장한다. 할당 이후 수정될 수 없다.
     */
    private final Type type;

    /**
     * ACE_BONUS는 1(ACE) 카드를 1점 혹은 11점으로 계산할 수 있으므로, 10점의 보너스가 부여된다는 의미이다.
     */
    public static final int ACE_BONUS = 10;

    /**
     * MAX_SCORE는 점수 계산 과정에서 넘으면 패배하는 상한선 값이다.
     */
    private static final int MAX_SCORE = 21;

    /**
     * 생성자 메서드는 symbol값과 type값을 받아 이를 할당해준다.
     *
     * @param symbol 할당될 심볼 값이다.
     * @param type 할당될 타입 값이다.
     */
    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    /**
     * getCardName은 카드의 심볼과 타입을 합친 값이다.
     * 하단의 override된 toString 메서드와는 별개로, 출력을 위해 설계하였다.
     *
     * @return 심볼과 타입을 합친 카드의 분류값(ex : 1스페이드, 3하트)을 반환한다.
     */
    public String getCardName() {
        return symbol.getName()+type.getType();
    }

    /**
     * getScore는 카드의 심볼에 따른 Score를 반환해준다.
     *
     * @return 카드의 Score를 반환한다.
     */
    public int getScore() {
        return symbol.getScore();
    }
    public int getAceScore(int score) {
        if(symbol.equals(ACE) && score <= MAX_SCORE - ACE_BONUS) {
            return ACE_BONUS;
        }
        return 0;
    }

    /**
     * equals는 카드간 비교를 위해 override한 메서드이다.
     * @param o 임의의 객체를 받아 이 값과 객체를 비교한다.
     * @return 파라미터로 받은 객체와 이 객체가 같은지 여부를 반환한다.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol &&
                type == card.type;
    }

    /**
     * hashCode는 equals 연산을 위한 해쉬값 리턴 메서드이다.
     *
     * @return 이 객체의 해쉬값을 반환해준다.
     */
    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    /**
     * toString은 본 객체의 정보를 String으로 반환해준다.
     *
     * @return 카드의 정보를 담은 String 값을 반환한다.
     */
    @Override
    public String toString() {
        return "Card{" +
                "symbol=" + symbol +
                ", type=" + type +
                '}';
    }
}
