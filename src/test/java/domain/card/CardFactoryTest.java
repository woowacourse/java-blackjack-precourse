package domain.card;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    void create() {
        Set<Card> cards = CardFactory.create();
        assertThat(cards).hasSize(52);
        System.out.println(cards);
    }
}
