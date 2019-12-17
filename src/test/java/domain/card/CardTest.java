package domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    public void cardTest(){
        Card card = new Card(Symbol.ACE, Type.DIAMOND);
        assertEquals(card.getSymbol(),Symbol.ACE);
    }

}