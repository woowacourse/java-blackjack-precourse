package view.output;

import domain.card.Deck;
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

    @Test
    public void cardStateTest() {
        Output output = new Output();
        Table table = new Table();
        Deck deck = new Deck();

        table.addMember(new Player("Kim", 8000));
        table.getTable().get(1).pickCardFromDeck(deck);
        table.getTable().get(1).pickCardFromDeck(deck);
        table.addMember(new Player("Kang", 7000));
        table.getTable().get(2).pickCardFromDeck(deck);
        table.getTable().get(2).pickCardFromDeck(deck);

        output.showMessageHavingCard(table.getTable());
    }

    @Test
    public void resultTest() {
        Output output = new Output();
        Table table = new Table();
        Deck deck = new Deck();

        table.getTable().get(0).pickCardFromDeck(deck);
        table.getTable().get(0).pickCardFromDeck(deck);
        table.getTable().get(0).pickCardFromDeck(deck);
        table.addMember(new Player("Kim", 8000));
        table.getTable().get(1).pickCardFromDeck(deck);
        table.getTable().get(1).pickCardFromDeck(deck);
        table.getTable().get(1).pickCardFromDeck(deck);
        table.addMember(new Player("Kang", 7000));
        table.getTable().get(2).pickCardFromDeck(deck);
        table.getTable().get(2).pickCardFromDeck(deck);
        table.getTable().get(2).pickCardFromDeck(deck);
        table.addMember(new Player("Hans", 10000));
        table.getTable().get(3).pickCardFromDeck(deck);
        table.getTable().get(3).pickCardFromDeck(deck);
        table.getTable().get(3).pickCardFromDeck(deck);

        output.showMessageResult(table.getTable());
        output.showMessageResultMoney(table);
    }
}