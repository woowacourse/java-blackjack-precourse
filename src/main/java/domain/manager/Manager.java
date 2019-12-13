package domain.manager;

import domain.card.Deck;
import domain.user.Player;
import domain.user.Table;
import view.output.Output;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {
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
        processManagementDealer(table.getTable()
                .get(Manuel.DEALER_POSITION.getValue()));
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
            return Manuel.REMOVE_ERROR.getValue();
        }
        bettingMoneys.add(bettingMoney);

        return Manuel.CONTINUE.getValue();
    }

    private void processManagementInputPlayers() {
        for (int i = 0; i < names.size(); i++) {
            table.addMember(new Player(names.get(i), bettingMoneys.get(i)));
        }
    }

    private void processManagementCardDispensing(List<Player> players) {
        players.get(Manuel.DEALER_POSITION.getValue()).pickCardFromDeck(deck);
        for (int i = Manuel.PLAYER_INIT.getValue(); i < players.size(); i++) {
            players.get(i).pickCardFromDeck(deck);
            players.get(i).pickCardFromDeck(deck);
        }
        output.showMessageDispensing(players);
        output.showMessageHavingCard(players);
    }

    private void processManagementGetOneMoreCard(List<Player> players) {
        for (int i = Manuel.PLAYER_INIT.getValue(); i < players.size(); i++) {
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

    private boolean pickOneMoreCardState(
            List<Player> players, int index, boolean selectDraw) {
        if (!selectDraw) {
            return false;
        }
        players.get(index).pickCardFromDeck(deck);
        output.showMessageHavingCard(players.get(index));
        if (players.get(index).calculateScore() > Manuel.BURST.getValue()) {
            output.showMessageBurst(players.get(index).getName());
            return false;
        }
        return true;
    }

    private void processManagementDealer(Player dealer) {
        boolean loop = false;

        if (dealer.calculateScore() <= Manuel.DEALER_MIN.getValue()) {
            loop = true;
        }
        while (loop) {
            System.out.println();
            loop = processManagementDealerGetOneMore(dealer);
        }
    }

    private boolean processManagementDealerGetOneMore(Player dealer) {
        if (dealer.calculateScore() <= Manuel.DEALER_MIN.getValue()) {
            output.showMessageDealerGetCard();
            dealer.pickCardFromDeck(deck);
            output.showMessageHavingCard(dealer);
            System.out.println("딜러 카드합 : " + dealer.calculateScore());
        }
        if (dealer.calculateScore() > Manuel.BURST.getValue()) {
            output.showMessageBurst(dealer.getName());
            return false;
        }
        return dealer.calculateScore() <= Manuel.DEALER_MIN.getValue();
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
