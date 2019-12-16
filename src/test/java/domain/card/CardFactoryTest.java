package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @DisplayName("Card Factory")
    @Test
    void create() {
        List<Card> cards = CardFactory.create();
        assertThat(cards).hasSize(52);
        System.out.println(cards);
    }
}
