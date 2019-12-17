package domain.card;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import annotation.TestDescription;
import org.junit.jupiter.api.Test;

class DeckTest {

    private static final int DECK_SIZE = 52;

    @Test
    @TestDescription("싱글톤 패턴이 적용되었는지 테스트합니다.")
    public void constructorTest() {
        Deck first = Deck.getInstance();
        Deck second = Deck.getInstance();
        assertEquals(first, second);
    }

//    @Test
//    public void getRandomCardTest(){
//        Deck deck = Deck.getInstance();
//        List<Card> cards = new ArrayList<>();
//        for(int i = 0; i<DECK_SIZE; i++){
//            cards.add(deck.getRandomCard());
//        }
//        System.out.println(cards);
//        long count = cards.stream().map(s -> s.equals(cards.stream())).count();
//        System.out.println(count);
////        assertThat(count).isEqualTo(52);
//    }

}