package utils;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutput {
    public static void printCards(String name, List<Card> cards) {
        System.out.println(name+" 카드: "+concatCardList(cards));
    }

    public static String concatCardList(List<Card> cards) {
        return String.join(",", getKoreanName(cards));
    }

    private static List<String> getKoreanName(List<Card> cards) {
        List<String> names = new ArrayList<>();
        cards.stream().forEach(x -> names.add(x.toString()));
        return names;
    }

    public static void printCardStatus(List<Player> players, Dealer dealer) {
        dealer.printFirstCard();
        players.stream().forEach(x -> x.printCards());
    }

}
