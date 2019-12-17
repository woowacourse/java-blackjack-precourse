package utils;

import domain.user.Dealer;
import domain.user.Player;

public class CardsPrinter {
    public static void printWithName(Player player) {
        System.out.println(player.getName() + " 카드 : " + player.getCards().toString());
    }

    public static void printWithName(Dealer dealer) {
        System.out.println("\n딜러 카드 : " + dealer.getCards().toString());
    }

    public static void printWithOneName(Dealer dealer) {
        System.out.println("\n딜러 카드 : " + dealer.getCards().get(0).toString());
    }

    public static void printWithScore(Player player) {
        System.out.println(
                player.getName() + " 카드 : " + player.getCards().toString() + " - 결과 : " + player.calculateFinalScore());
    }

    public static void printWithScore(Dealer dealer) {
        System.out.println("\n딜러 카드 : " + dealer.getCards().toString() + " - 결과 : " + dealer.calculateFinalScore());
    }
}
