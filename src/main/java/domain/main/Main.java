package domain.main;

public class Main {

    private static OutputPrint outputPrint = new OutputPrint();

    public static void main(String[] args) {
        outputPrint.getPlayerNames();
        outputPrint.giveCardsFirstToPlayers();
        outputPrint.choiceMoreCard();
        outputPrint.dealerLessThan16();
        outputPrint.printFinalLog();

    }

}
