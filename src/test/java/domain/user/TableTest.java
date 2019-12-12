package domain.user;

import domain.card.Deck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TableTest {
    @Test
    public void tableTest() {
        Table table = new Table();

        table.addMember(new Player("kim", 5000));
        table.addMember(new Player("kang", 7000));

        assertThat(table.getTable().size()).isEqualTo(3);
        assertThat(table.getTable().get(1).getName()).isEqualTo("kim");
        assertThat(table.getTable().get(2).getBettingMoney()).isEqualTo(7000);
    }

    @Test
    // 임시로 테스트를 진행
    public void balanceTest1() {
        Table table = new Table();
        Deck deck = new Deck();

        table.addMember(new Player("kim", 5000));
        table.addMember(new Player("kang", 7000));

        table.getTable().get(0).pickCardFromDeck(deck);

        table.getTable().get(1).pickCardFromDeck(deck);
        table.getTable().get(1).pickCardFromDeck(deck);

        table.getTable().get(2).pickCardFromDeck(deck);
        table.getTable().get(2).pickCardFromDeck(deck);

        table.calculateMoney().forEach(System.out::println);
    }
}