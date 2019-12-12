package domain.manager;

import domain.card.Deck;
import domain.user.Table;
import view.output.Output;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Manager {
    Deck deck = new Deck();
    Table table = new Table();
    Output output = new Output();

    public void start() {
        output.showMessageInputName();
    }

    public void processManagement() {

    }

    public void end() {

    }

    public void splitName(String name) {
        List<String> names = Arrays
                .stream(name.split(","))
                .collect(Collectors.toList());
    }
}
