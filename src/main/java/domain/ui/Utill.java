package domain.ui;

import domain.user.GameParticipant;

import java.util.*;

public class Utill {

    private ArrayList<String> playerNameList = new ArrayList<String>();
    private ArrayList<Double> playerBattingMoneyList = new ArrayList<Double>();

    public void initPlayerNameAndPlayerBattingMoney() {
        inputPlayerNameList();
        inputPlayerBattingList();
    }

    public ArrayList<String> inputPlayerNameList() {
        Scanner sc = new Scanner(System.in);

        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Collections.addAll(playerNameList, sc.next().split(","));

        return playerNameList;
    }

    public ArrayList<Double> inputPlayerBattingList() {
        Scanner sc = new Scanner(System.in);

        for (String name : playerNameList) {
            System.out.println(name + "의 배팅 금액은?");
            playerBattingMoneyList.add(sc.nextDouble());
        }

        return playerBattingMoneyList;
    }

    public void printCardListOfGameParticipant(GameParticipant participant) {
        System.out.println(participant.getName() + participant.getCards().toString());
    }

    public boolean askNeedMoreCard(GameParticipant p) {
        Scanner sc = new Scanner(System.in);

        if (p.isBust()) {
            return false;
        }
        System.out.println(p.getName() + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return sc.next() == "y";
    }

}
