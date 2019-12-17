package domain.card;

import annotation.TestDescription;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardFactoryTest {
    @Test
    void create() {
        List<Card> cards = CardFactory.create();
        assertThat(cards).hasSize(52);
        System.out.println(cards);
    }

    @Test
    @TestDescription("52장의 카드가 서로 다른 카드인지를 확인합니다.")
    public void cardsDuplicationTest(){
        List<Card> cards = CardFactory.create();
        long count = cards.stream()
                .map(s -> s.equals(cards.stream()))
                .count();
        assertThat(count).isEqualTo(52);
    }
}
