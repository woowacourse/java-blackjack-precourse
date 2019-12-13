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
    Validator validator = new Validator();
    List<String> names;

    public void start() {
        processManagementInputNames(true);
    }

    private void processManagementInputNames(boolean isErrorOccurred) {
        while (isErrorOccurred) {
            names = splitName(output.showMessageInputName());
            isErrorOccurred = validator.checkName(names);
        }
    }

    private void end() {

    }

    private List<String> splitName(String name) {
        List<String> names = Arrays
                .stream(name.split(","))
                .collect(Collectors.toList());

        return names;
    }
}
