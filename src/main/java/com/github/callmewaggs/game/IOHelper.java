package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.user.Participant;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IOHelper {

  private static final Scanner scanner = new Scanner(System.in);

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

  public static String inputHitOrStay(String name) {
    printMessage("\n" + name + "은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    return scanner.nextLine();
  }

  private static void printMessage(String message) {
    System.out.println(message);
  }

  static void printAfterDealInitialCards(List<Participant> participants) {
    printNames(participants);
    printCardsWithoutScore(participants);
  }

  private static void printNames(List<Participant> participants) {
    String names =
        participants.stream().map(Participant::getName).collect(Collectors.joining(", "));
    printMessage("\n" + names + "에게 2장의 카드를 나누었습니다.");
  }

  private static void printCardsWithoutScore(List<Participant> participants) {
    for (Participant participant : participants) {
      printCardsWithoutScore(participant);
    }
  }

  static void printCardsWithScore(List<Participant> participants) {
    for (Participant participant : participants) {
      printCardsWithScore(participant, String.valueOf(participant.getCurrentScore()));
    }
  }

  static void printCardsWithoutScore(Participant participant) {
    printCardsWithScore(participant, null);
  }

  private static void printCardsWithScore(Participant participant, String score) {
    if (score == null) {
      printMessage(nameAndCardsMessage(participant, participant.getName()));
      return;
    }
    printMessage(nameAndCardsMessage(participant, participant.getName()) + " - 결과 : " + score);
  }

  private static String nameAndCardsMessage(Participant participant, String name) {
    return name + " : " + participant.getCurrentCardsInfo();
  }

  public static void printDealerHitOrStayMessage(boolean result) {
    if (result) {
      printMessage("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
      return;
    }
    printMessage("\n딜러는 17이상이라 카드를 추가로 받지 않았습니다.");
  }

  public static void printHitRejectedMessage(String name) {
    printMessage(name + "은/는 점수의 총합이 21을 초과하므로 카드를 더 받을 수 없습니다.");
  }

  static void printFinalResultMessage() {
    printMessage("\n## 최종 수익");
  }

  static void printNameAndIncome(Map<String, Double> participantsIncome) {
    participantsIncome.forEach((name, income) -> printMessage(name + " : " + income));
  }

  static void printBlackjackMessage(String name) {
    printMessage(name + "은/는 블랙잭입니다!!!");
  }
}
