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

    @Test
    public void getScoreTest() {
        Card card = new Card(Symbol.SEVEN, Type.SPADE);
        Card card2 = new Card(Symbol.ACE, Type.SPADE);
        Card card3 = new Card(Symbol.KING, Type.SPADE);
        Card card4 = new Card(Symbol.TEN, Type.SPADE);

        assertThat(card.getScore()).isEqualTo(7);
        assertThat(card2.getScore()).isEqualTo(1);
        assertThat(card3.getScore()).isEqualTo(10);
        assertThat(card4.getScore()).isEqualTo(10);
    }
}