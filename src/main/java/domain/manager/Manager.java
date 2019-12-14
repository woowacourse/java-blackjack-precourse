package domain.manager;

import domain.card.Deck;
import domain.user.Player;
import domain.user.Table;
import view.output.Output;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {
    private Deck deck = new Deck();
    private Table table = new Table();
    private Output output = new Output();
    private Validator validator = new Validator();
    private List<String> names;
    private List<Double> bettingMoneys = new LinkedList<>();

    public void start() {
        processManagementInputNames();
        processManagementInputBettingMoney();
        processManagementInputPlayers();
        processManagementCardDispensing(table.getTable());
        processManagementGetOneMoreCard(table.getTable());
        processManagementDealer(table.getTable().get(Manual.DEALER_POSITION.getValue()));
        end(table);
    }

    private void processManagementInputNames() {
        boolean isErrorOccurred = true;

        while (isErrorOccurred) {
            names = splitName(output.showMessageInputName());
            isErrorOccurred = validator.isContainsSpace(names) || validator.hasOverlap(names);
        }
    }

    private void processManagementInputBettingMoney() {
        for (int i = 0; i < names.size(); i++) {
            i -= checkMoneyValidating(output.showMessageInputMoney(names.get(i)));
            output.newLine();
        }
    }

    private int checkMoneyValidating(Double bettingMoney) {
        if (validator.isBelowZero(bettingMoney)) {
            output.showMessageMisInputErrorReturn();

            return Manual.REMOVE_ERROR.getValue();
        }

        bettingMoneys.add(bettingMoney);

        return Manual.CONTINUE.getValue();
    }

    private void processManagementInputPlayers() {
        for (int i = 0; i < names.size(); i++) {
            table.addMember(new Player(names.get(i), bettingMoneys.get(i)));
        }
    }

    private void processManagementCardDispensing(List<Player> players) {
        players.get(Manual.DEALER_POSITION.getValue()).pickCardFromDeck(deck);

        for (int i = Manual.PLAYER_INIT.getValue(); i < players.size(); i++) {
            players.get(i).pickCardFromDeck(deck);
            players.get(i).pickCardFromDeck(deck);
        }

        output.showMessageDispensing(players);
        output.showMessageHavingCard(players);
    }

    private void processManagementGetOneMoreCard(List<Player> players) {
        for (int i = Manual.PLAYER_INIT.getValue(); i < players.size(); i++) {
            hasBlackjack(players, i);
        }

        table.iteratePlayer();
    }

    private void hasBlackjack(List<Player> players, int index) {
        if (players.get(index).calculateScore() != Manual.BLACKJACK.getValue()) {
            pickOneMoreCard(players, index);
        }
    }

    private void pickOneMoreCard(List<Player> players, int index) {
        boolean loop = true;

        output.newLine();
        output.showMessageHavingCard(players.get(index));

        while (loop) {
            loop = pickOneMoreCardState(players, index, output.showMessageOneMore(players.get(index).getName()));
        }
    }

    private boolean pickOneMoreCardState(List<Player> players, int index, boolean selectDraw) {
        if (!selectDraw) {
            return false;
        }

        players.get(index).pickCardFromDeck(deck);
        output.showMessageHavingCard(players.get(index));

        return isNotBurst(players, index);
    }

    private boolean isNotBurst(List<Player> players, int index) {
        if (players.get(index).calculateScore() > Manual.BURST.getValue()) {
            output.showMessageBurst(players.get(index).getName());
            return false;
        }

        return true;
    }

    private void processManagementDealer(Player dealer) {
        boolean loop = false;

        if (dealer.calculateScore() <= Manual.DEALER_MIN.getValue()) {
            loop = true;
        }

        while (loop) {
            output.newLine();
            loop = processManagementDealerGetOneMore(dealer);
        }
    }

    private boolean processManagementDealerGetOneMore(Player dealer) {
        if (dealer.calculateScore() <= Manual.DEALER_MIN.getValue()) {
            dealerDrawOneMoreCard(dealer);
        }

        if (dealer.calculateScore() > Manual.BURST.getValue()) {
            output.showMessageBurst(dealer.getName());
            return false;
        }

        return dealer.calculateScore() <= Manual.DEALER_MIN.getValue();
    }

    private void dealerDrawOneMoreCard(Player dealer) {
        output.showMessageDealerGetCard();
        dealer.pickCardFromDeck(deck);
        output.showMessageHavingCard(dealer);
        hasBlackjackDealer(dealer);
    }

    private void hasBlackjackDealer(Player dealer) {
        if (dealer.calculateScore() == Manual.BLACKJACK.getValue()
                && dealer.getCards().size() == Manual.Pair.getValue()) {
            List<Boolean> blackjack = table.getBlackjack();

            blackjack.set(Manual.DEALER_POSITION.getValue(), true);
            table.setBlackjack(blackjack);
        }
    }

    private void processManagementFinalResult(List<Player> player) {
        output.showMessageResult(player);
    }

    public void processManagementBalance(Table table) {
        output.showMessageResultMoney(table);
    }

    private void end(Table table) {
        output.newLine();
        processManagementFinalResult(table.getTable());
        output.newLine();
        processManagementBalance(table);
    }

    private List<String> splitName(String name) {
        return Arrays.stream(name.split(",")).collect(Collectors.toList());
    }
}
