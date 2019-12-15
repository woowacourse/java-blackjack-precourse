package domain.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolTest {

    Symbol symbol;


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