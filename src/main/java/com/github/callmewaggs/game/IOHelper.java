package com.github.callmewaggs.game;

import java.util.Scanner;

public class IOHelper {

  private static Scanner scanner = new Scanner(System.in);

  public static void printExceptionMessage(String message) {
    printMessage(message);
  }

  public static String inputPlayerNames() {
    printMessage("게임에 참여할 사람의 이름을 입력하세요.");
    return scanner.nextLine();
  }

  public static String inputBettingMoney(String name) {
    printMessage("\n" + name + "의 배팅 금액은?");
    return scanner.nextLine();
  }

  private static void printMessage(String message) {
    System.out.println(message);
  }
}
