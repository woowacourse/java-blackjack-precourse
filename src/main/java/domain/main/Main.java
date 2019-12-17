package domain.main;

public class Main {

    private static OutputPrint outputPrint = new OutputPrint();

    public static void main(String[] args) {
        System.out.print(outputPrint.getPlayerNames());
        outputPrint.giveCardsFirstToPlayers();
        outputPrint.choiceMoreCard();

    }

}
