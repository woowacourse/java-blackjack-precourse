package domain;

import domain.card.Card;
import domain.user.Participant;
import domain.user.Player;

import java.util.List;
import java.util.Scanner;

/**
 * 게임에 필요한 입,출력을 담당하는 객체
 */
public class GamePrinter {
    Scanner scanner = new Scanner(System.in);

    String[] getPlayerNameFromUser() {
        String playerNameFromUser;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            playerNameFromUser = scanner.nextLine();
        } while (!checkPlayerName(playerNameFromUser));
        String[] splitedPlyerName = splitPlayerName(playerNameFromUser);
        return splitedPlyerName;
    }

    String[] splitPlayerName(String playerNameFromUser) {
        String[] splitedPlayerName = playerNameFromUser.split(",");
        return splitedPlayerName;
    }

    boolean checkPlayerName(String playerNameFromUser) {
        if (playerNameFromUser.length() == 0
                || playerNameFromUser.charAt(playerNameFromUser.length() - 1) == ',') {
            System.out.println("이름을 잘못 입력했습니다.");
            return false;
        }
        return true;
    }

    int getBettingMoneyFromUser(String name) {
        System.out.println(name + "의 배팅 금액은?");
        int bettingMoney;
        try {
            bettingMoney = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            bettingMoney = -1; // 문자이거나 정수가 아니라면 -1을 반환한다.
        }

        if (checkBettingMoney(bettingMoney))
            return bettingMoney;
        return getBettingMoneyFromUser(name);
    }

    boolean checkBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            System.out.println("배팅 금액이 잘못되었습니다.");
            return false;
        }
        return true;
    }

    void printInitialParticipantCardInfo(List<Participant> participantList) {
        for (int i = 0; i < participantList.size() - 1; i++) {
            System.out.print(participantList.get(i).getName() + ", ");
        }
        System.out.println(participantList.get(participantList.size() - 1).getName() + "에게 2장의 카드를 나누어주었습니다.");
    }

    void printParticipantCardInfo(Participant participant, boolean onlyOpenOneCard) {
        System.out.print(participant.getName() + "카드:");
        int cardSize = participant.getCards().size();
        if (onlyOpenOneCard) {
            cardSize = 1;
        }
        for (int i = 0; i < cardSize - 1; i++) {
            printCardInfo(participant.getCard(i));
            System.out.print(",");
        }
        printCardInfo(participant.getCard(cardSize - 1));
        System.out.println();
    }

    void printCardInfo(Card card) {
        System.out.print(card.getSymbol().getScore() + card.getType().getKoreaType());
    }
}
