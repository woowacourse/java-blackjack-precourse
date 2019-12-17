package domain.main;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static OutputPrint outputPrint = new OutputPrint();

    public static void main(String[] args) {
        System.out.print(outputPrint.getPlayerNames());
        outputPrint.giveCardsFirstToPlayers();

    }

}
