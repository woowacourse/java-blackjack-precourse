package main;

import domain.user.Gamers;
import domain.user.Player;
import utils.BooleanInput;
import utils.CardsPrinter;
import utils.EarningCalculator;
import utils.StatePrinter;

/**
 * 블랙잭 게임을 진행하는 객체
 */
public class BlackJackGame {
    private static final String TEMP_DEALER_NAME = "딜러";
    Gamers gamers = new Gamers();

    public void start() {
        gamers.drawInit();
        StatePrinter.printInitDrawMessage(gamers.getPlayerNames());
        printTable();
        play();
        printResult();
    }
    
    private void play() {
        if (gamers.getDealer().isBlackJack()) {
            StatePrinter.printDealerBlackJack();
            return;
        }
        
        drawGamersCard();
    }

    private void drawPlayersCard() {
        for (Player player : gamers.getPlayers()) {
            drawPlayerCard(player);
        }
    }

    private void drawPlayerCard(Player player) {
        if (player.isBust()) {
            return;
        }
        
        askDraw(player);
    }
    
    private void askDraw(Player player) {
        BooleanInput booleanInput = new BooleanInput(player.getName());
        if (!booleanInput.getAgree()) {
            return;
        }
        
        player.draw();
        CardsPrinter.printWithName(player);
        drawPlayerCard(player);
    }

    private void drawDealerCard() {
        while (gamers.getDealer().isHittable()) {
            gamers.getDealer().draw();
            StatePrinter.printDealerDrawMessage();
        }
    }

    private void printTable() {
        CardsPrinter.printWithOneName(gamers.getDealer());
        for (Player player : gamers.getPlayers()) {
            CardsPrinter.printWithName(player);
        }
        System.out.println();
    }

    private void printTableWithScore() {
        CardsPrinter.printWithScore(gamers.getDealer());
        for (Player player : gamers.getPlayers()) {
            CardsPrinter.printWithScore(player);
        }
    }

    private void printFinalEarning() {
        StatePrinter.printEarningMessage();
        StatePrinter.printEarning(TEMP_DEALER_NAME, 
                -EarningCalculator.calculateTotalEarning(gamers));
        
        for (Player player : gamers.getPlayers()) {
            StatePrinter.printEarning(player.getName(), 
                    EarningCalculator.calculateEarning(player, gamers.getDealer()));
        }
    }

    private void printResult() {
        printTableWithScore();
        printFinalEarning();
    }

    private void drawGamersCard() {
        drawPlayersCard();
        drawDealerCard();
    }
}
