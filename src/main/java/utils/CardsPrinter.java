package utils;

import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;

/**
 * gamer가 들고 있는 현재 카드 상황을 출력하는 객체
 */
public class CardsPrinter {
    public static String CardToString(Gamer gamer) {
        return gamer.getCards().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", "));
    }
    
    public static void printWithName(Player player) {
        System.out.println(player.getName() 
                + " 카드 : " 
                + CardToString(player));
    }

    public static void printWithOneName(Dealer dealer) {
        System.out.println("\n딜러 카드 : " 
                + dealer.getCards().get(0).toString());
    }

    public static void printWithScore(Player player) {
        System.out.println(player.getName() 
                + " 카드 : " 
                + CardToString(player)
                + " - 결과 : " 
                + player.calculateFinalScore());
    }

    public static void printWithScore(Dealer dealer) {
        System.out.println("\n딜러 카드 : " 
                + CardToString(dealer)
                + " - 결과 : " 
                + dealer.calculateFinalScore());
    }
}
