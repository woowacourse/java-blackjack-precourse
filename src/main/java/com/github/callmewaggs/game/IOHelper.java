package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.user.Participant;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

  public static void printAfterDealInitialCards(List<Participant> participants) {
    printNames(participants);
    printCards(participants);
  }

  private static void printNames(List<Participant> participants) {
    String names =
        participants.stream().map(Participant::getName).collect(Collectors.joining(", "));
    printMessage("\n" + names + "에게 2장의 카드를 나누었습니다.");
  }

  private static void printCards(List<Participant> participants) {
    for (Participant participant : participants) {
      printCardsWithScore(participant, null);
    }
  }

  public static void printCardsWithScore(Participant participant, String score) {
    if (score == null) {
      printMessage(nameAndCardsMessage(participant, participant.getName()));
      return;
    }
    printMessage(nameAndCardsMessage(participant, participant.getName()) + " - 결과 : " + score);
  }

  private static String nameAndCardsMessage(Participant participant, String name) {
    return name + " : " + participant.getCardsInfo();
  }

}
