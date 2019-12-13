package domain.manager;

import com.sun.corba.se.impl.io.TypeMismatchException;
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
    List<Double> bettingMoneys;
    private boolean isErrorOccurred;

    public void start() {
        processManagementInputNames();
        processManagementInputBettingMoney();
    }

    private void processManagementInputNames() {
        isErrorOccurred = true;
        while (isErrorOccurred) {
            names = splitName(output.showMessageInputName());
            isErrorOccurred = validator.isContainsSpace(names);
        }
    }

    private void processManagementInputBettingMoney() {
        try {
            inputBettingMoneyIterating();
            checkMoneyValidating();
        } catch (TypeMismatchException e) {
            output.showMessageMisInputErrorReturn();
        }
    }

    private void inputBettingMoneyIterating() {
        for (String name : names) {
            bettingMoneys.add(output.showMessageInputMoney(name));
        }
    }

    private void checkMoneyValidating() {
        isErrorOccurred = true;

        while (isErrorOccurred) {
            isErrorOccurred = validator.isBelowZero(bettingMoneys);
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
