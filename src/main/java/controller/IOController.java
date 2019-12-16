package controller;

import java.util.Scanner;

public class IOController {

  private static void askNames() {
    System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표기준으로 분리)");
  }

  private static String inputNames() {
    Scanner scanner = new Scanner(System.in);
    String names = scanner.nextLine();
    return names;
  }

  private static String[] parseNames(String names) {
    String[] users = names.split(",");

    for (int i = 0; i < users.length; i++) {
      users[i] = users[i].trim();
    }

    return users;
  }

  private static void validateName(String name) throws Exception {
    if (name.length() == 0) {
      throw new Exception("이름은 공백으로 이루어질 수 없습니다.");
    }
  }

  private static void validateNames(String[] names) throws Exception {
    if (names.length == 0) {
      throw new Exception("이름이 유효하지 않습니다.");
    }

    if (names.length > 6) {
      throw new Exception("6명까지 플레이가 가능합니다.");
    }

    for (String name : names) {
      validateName(name);
    }
  }

  private static String[] getUsersAgain(String message) {
    System.out.println(message);
    return getUsers();
  }

  public static String[] getUsers() {
    askNames();
    String[] users = parseNames(inputNames());

    try {
      validateNames(users);

    } catch (Exception e) {
      return getUsersAgain(e.getMessage());
    }

    return users;
  }

  public static int[] getBetting(String[] users) {
    int[] betting = new int[users.length];

    for (int i = 0; i < users.length; i++) {
      betting[i] = getBetting(users[i]);
    }

    return betting;
  }

  private static void askBettingMoney(String name) {
    System.out.println(name + "의 베팅 금액은?");
  }

  private static void validateMoney(int money) throws Exception {
    if (money <= 0){
     throw new Exception("금액이 올바르지 않습니다.");
    }
  }

  private static int inputMoney() {
    try {
      Scanner scanner = new Scanner(System.in);
      int money = scanner.nextInt();

      validateMoney(money);

      return money;
    } catch (Exception e) {
      System.out.println("금액이 올바르지 않습니다(베팅 가능 금액 > 0)");
      return inputMoney();
    }
  }

  private static int getBetting(String user) {
    askBettingMoney(user);
    int money = inputMoney();

    return money;
  }
}
