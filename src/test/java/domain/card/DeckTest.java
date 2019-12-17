package domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import annotation.TestDescription;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @TestDescription("싱글톤 패턴이 적용되었는지 테스트합니다.")
    public void constructorTest() {
        Deck first = Deck.getInstance();
        Deck second = Deck.getInstance();
        assertEquals(first, second);
    }
}