package view;

import java.util.List;

/**
 * ConsoleOutput.java
 * 콘솔로 출력되는 모든 것들
 * 우아한테크코스 프리코스 3주차
 * Original code https://github.com/hotheadfactory/java-blackjack-precourse
 * Version: v0.0.1, 2019.12.16 (c) 정회형
 */
public class ConsoleOutput {
    public static void printCards(String cardString) {
        System.out.println(cardString);
    }

    public static void printMessage(String string) {
        System.out.println(string);
    }

    public static void printBusted() {
        System.out.println("\n버스트! 배팅 금액을 모두 잃습니다.");
    }

    public static void printDealerRedraw() {
        System.out.println("\n딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public static void printBlackJack() {
        System.out.println("블랙잭! 150%의 배당을 받습니다.");
    }

    public static void printFirstDraw(String user, int cardNumber) {
        System.out.println("딜러와 " + user + "에게 " + cardNumber + "장의 카드를 나누었습니다.");
    }

    public static void printWinner(List<MoneyDTO> playerMoney, MoneyDTO dealerMoney) {
        printMessage("## 최종 수익");
        printMessage("딜러: " + dealerMoney.getMoney());
        playerMoney.forEach(x -> printMessage(x.getName() + ": " + x.getMoney()));
    }
}
