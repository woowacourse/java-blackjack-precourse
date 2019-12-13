package domain.card;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class CardPrinter {

    public static void printDealerFirstCardOnly(Dealer dealer) {
        String firstCardName = dealer.getFirstCardName();
        System.out.println(String.format("딜러 카드: %s", firstCardName));
    }

    public static void printPlayerAllCards(List<Player> playerList, List<String> playerNameList) {
        for (int i = 0; i < playerList.size(); i++) {
            String playerName = playerNameList.get(i);
            String cardNames = playerList.get(i).getAllCardNames();
            System.out.println(String.format("%s 카드: %s", playerName, cardNames));
        }
    }

}
