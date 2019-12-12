package view.output;

import domain.user.Player;
import domain.user.Table;
import org.junit.jupiter.api.Test;

class outputTest {
    @Test
    public void dispenseTest() {
        Output output = new Output();
        Table table = new Table();

        table.addMember(new Player("Kim", 8000));
        table.addMember(new Player("Kang", 7000));
        table.addMember(new Player("Hans", 10000));

        output.showMessageDispensing(table.getTable());
    }

}