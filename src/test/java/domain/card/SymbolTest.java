package domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SymbolTest {

    private Symbol symbol;


    @Test
    @DisplayName("#toString: 무늬가 문자(예: A)일 때")
    void toStringOfAce() {
        symbol = Symbol.ACE;
        assertEquals("A", symbol.toString());
    }

    @Test
    @DisplayName("#toString: 무늬가 숫자일 때")
    void toStringNotAce() {
        symbol = Symbol.TWO;
        assertEquals(String.valueOf(symbol.getScore()), symbol.toString());
    }
}