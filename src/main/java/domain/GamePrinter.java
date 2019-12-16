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

    double getBettingMoneyFromUser(String name) {
        System.out.println(name + "의 배팅 금액은?");
        double bettingMoney;
        try {
            bettingMoney = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            bettingMoney = -1; // 문자이거나 정수가 아니라면 -1을 반환한다.
        }

        if (checkBettingMoney(bettingMoney))
            return bettingMoney;
        return getBettingMoneyFromUser(name);
    }

    boolean checkBettingMoney(double bettingMoney) {
        if (bettingMoney <= 0) {
            System.out.println("배팅 금액이 잘못되었습니다.");
            return false;
        }
        return true;
    }

    void printInitialParticipantCardInfo(List<Participant> participantList) {
        for (int i = 0; i < participantList.size() - 1; i++)
            System.out.print(participantList.get(i).getName() + ", ");
        System.out.println(participantList.get(participantList.size() - 1).getName() + "에게 2장의 카드를 나누어주었습니다.");

        // 딜러는 1장의 카드만 공개한다.
        callPrintParticipantCardInfo(participantList.get(0), true, false);
        for (int i = 1; i < participantList.size(); i++)
            callPrintParticipantCardInfo(participantList.get(i), false, false);
    }

    // 참여자의 이름과 카드 정보, 경우에 따라 결과 스코어를 출력하는 함수
    void callPrintParticipantCardInfo(Participant participant, boolean onlyOpenOneCard, boolean printWithPoint) {
        System.out.print(participant.getName() + "카드:");
        int cardSize = participant.getCards().size();
        if (onlyOpenOneCard) {
            cardSize = 1;
        }
        printCardInfo(participant, cardSize);
        printScoreInfo(participant, printWithPoint);
        System.out.println();
    }

    void printScoreInfo(Participant participant, boolean print){
        if(print)
            System.out.print(" - 결과: "+participant.getSumScore());
    }

    // 참여자의 카드 정보를 출력하는 함수
    void printCardInfo(Participant participant, int cardSize) {
        List<Card> cards = participant.getCards();
        for (int i = 0; i < cardSize - 1; i++) {
            System.out.print(cards.get(i).getSymbol().getStr()+ cards.get(i).getType().getKoreaType());
            System.out.print(",");
        }
        System.out.print(cards.get(cardSize - 1).getSymbol().getScore() + cards.get(cardSize - 1).getType().getKoreaType());
    }

    // 플레이어가 추가적으로 카드를 받을지 여부를 입력받는 함수
    String getAddInfoDecisionFromUser(Participant participant){
        System.out.println(participant.getName()+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String decision = scanner.nextLine();
        if (decision.equals("y") || decision.equals("n")){
            return decision;
        }
        System.out.println("잘못 입력하셨습니다.");
        return getAddInfoDecisionFromUser(participant);
    }

    void printDealerGetMoreCard(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    void printTotalProfit(List<Participant> participants) {
        System.out.println("### 최종수익");
        for(int i = 0; i < participants.size(); i++)
            System.out.println(participants.get(i).getName() + ": "+ participants.get(i).getProfit()) ;
    }
}
