package domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SymbolTest {

    private Symbol symbol;


    @Test
    void toStringOfAce() {
        symbol = Symbol.ACE;
        assertEquals("A", symbol.toString());
    }
    @Test
    void toStringNotAce() {
        symbol = Symbol.TWO;
        assertEquals(String.valueOf(symbol.getScore()), symbol.toString());
    }
}