package io;

import java.util.ArrayList;

import domain.user.Dealer;
import domain.user.Player;

public class OutputSystem {
    static public void printGetName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
    }

    static public void printBettingPrice(String player) {
        System.out.println(player + "의 배팅금액은?");
    }

    static public void printGetTwoCards(ArrayList<Player> players) {
        ArrayList<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(player.getName());
        }
        String nameByCommas = String.join(",", names);
        System.out.println("딜러와 " + nameByCommas + "에게 2장의 나누었습니다.");
    }

    static private void printDealerCardList(Dealer dealer) {
        System.out.print("딜러 : 다이아몬드3");
    }

    static private void printUserCardList(Player player) {
        System.out.print("유저의카드 : 다이아몬드3");
    }

    static public void printPeopleCardList(Dealer dealer, ArrayList<Player> players) {
        printDealerCardList(dealer);
        System.out.println();
        for (Player player : players) {
            printUserCardList(player);
            System.out.println();
        }
    }

    static public void printUserGetCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받으시겠습니까?(예는 y, 아니오는 n)");
    }

    static public void printDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다");
    }

    static public void printResultValue(Dealer dealer, ArrayList<Player> players) {
        printDealerCardList(dealer);
        System.out.println("- 결과 : 21");
        for (Player player : players) {
            printUserCardList(player);
            System.out.println("- 결과 : 21");
        }
    }

    static public void printResultMoney(Dealer dealer, ArrayList<Player> players) {
        System.out.println("## 최종수익");
        System.out.print("딜러 : 10000");
        for (Player player : players) {
            System.out.println(player.getName() + ":" + "1234");
        }
    }

}