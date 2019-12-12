package domain.user;

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

}