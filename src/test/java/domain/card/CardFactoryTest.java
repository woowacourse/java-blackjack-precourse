package domain.card;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    void create() {
        List<Card> cards = CardFactory.create();
        assertThat(cards).hasSize(52);
        System.out.println(cards);
    }

    @Test
    void popRandomCard() {
        CardFactory cardFactory = new CardFactory();
        assertThat(cardFactory.cards).hasSize(52);
        Card card = cardFactory.popRandomCard();
        assertThat(cardFactory.cards).hasSize(51);
        assertThat(cardFactory.cards).doesNotContain(card);
    }
}
