package domain.manager;

import domain.card.Deck;
import domain.user.Player;
import domain.user.Table;
import view.output.Output;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {
    private static final int REMOVE_ERROR_VALUE = 1;
    private static final int CONTINUE_VALUE = 0;
    private static final int DEALER_POSITION = 0;
    private static final int BLACK_JACK_NUMBER = 10;
    private static final int BURST = 21;
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
        processManagementCardDispensing(table.getTable());
        processManagementGetOneMoreCard(table.getTable());
    }

    private void processManagementInputNames() {
        isErrorOccurred = true;
        while (isErrorOccurred) {
            names = splitName(output.showMessageInputName());
            isErrorOccurred = validator.isContainsSpace(names) || validator.hasOverlap(names);
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

    private void processManagementCardDispensing(List<Player> players) {
        players.get(DEALER_POSITION).pickCardFromDeck(deck);
        for (int i = 1; i < players.size(); i++) {
            players.get(i).pickCardFromDeck(deck);
            players.get(i).pickCardFromDeck(deck);
        }
        output.showMessageDispensing(players);
        output.showMessageHavingCard(players);
    }

    private void processManagementGetOneMoreCard(List<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            pickOneMoreCard(players, i);
        }
    }

    private void pickOneMoreCard(List<Player> players, int index) {
        boolean loop = true;

        System.out.println();
        while (loop) {
            loop = pickOneMoreCardState(players, index
                    , output.showMessageOneMore(players.get(index).getName()));
        }
    }

    private boolean pickOneMoreCardState(List<Player> players, int index, boolean selectDraw) {
        if (!selectDraw) {
            return false;
        }
        players.get(index).pickCardFromDeck(deck);
        output.showMessageHavingCard(players.get(index));
        if (players.get(index).calculateScore(BLACK_JACK_NUMBER) > BURST) {
            output.showMessageBurst(players.get(index).getName());
            return false;
        }
        return true;
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
