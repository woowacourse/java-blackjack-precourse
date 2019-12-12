package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Test
    public void cardInitTest() {
        Card card = new Card(Symbol.ACE, Type.SPADE);
        assertThat(card).isNotNull();
    }

    @Test
    public void cardEqualAndHashCodeTest() {
        Card card = new Card(Symbol.ACE, Type.SPADE);
        Card card2 = new Card(Symbol.ACE, Type.SPADE);
        Card card3 = new Card(Symbol.ACE, Type.HEART);
        Card card4 = new Card(Symbol.SEVEN, Type.SPADE);
        
        assertThat(card).isEqualTo(card2);
        assertThat(card).isNotEqualTo(card3);
        assertThat(card).isNotEqualTo(card4);
    }
}