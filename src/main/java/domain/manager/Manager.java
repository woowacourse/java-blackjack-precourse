package domain.manager;

import domain.card.Deck;
import domain.user.Player;
import domain.user.Table;
import view.output.Output;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Manager {
    private static final int REMOVE_ERROR_VALUE = 1;
    private static final int CONTINUE_VALUE = 0;
    Deck deck = new Deck();
    Table table = new Table();
    Output output = new Output();
    Validator validator = new Validator();
    List<String> names;
    List<Double> bettingMoneys = new LinkedList<>();
    private boolean isErrorOccurred;

    public void start() {
        processManagementInputNames();
        processManagementInputBettingMoney();
        processManagementInputPlayers();
    }

    private void processManagementInputNames() {
        isErrorOccurred = true;
        while (isErrorOccurred) {
            names = splitName(output.showMessageInputName());
            isErrorOccurred = validator.isContainsSpace(names);
        }
    }

    private void processManagementInputBettingMoney() {
        for (int i = 0; i < names.size(); i++) {
            Double bettingMoney = output.showMessageInputMoney(names.get(i));
            i -= checkMoneyValidating(bettingMoney);
            System.out.println();
        }
    }

    private int checkMoneyValidating(Double bettingMoney) {
        if (validator.isBelowZero(bettingMoney)) {
            output.showMessageMisInputErrorReturn();
            return REMOVE_ERROR_VALUE;
        }
        bettingMoneys.add(bettingMoney);

        return CONTINUE_VALUE;
    }

    private void processManagementInputPlayers() {
        for (int i = 0; i < names.size(); i++) {
            table.addMember(new Player(names.get(i), bettingMoneys.get(i)));
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
