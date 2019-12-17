package domain.ui;

import domain.user.GameParticipant;

import java.util.*;

public class Utill {

    public ArrayList<String> inputPlayerNameList() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> playerNameList = new ArrayList<String>();

        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Collections.addAll(playerNameList, sc.next().split(","));

        return playerNameList;
    }

    public ArrayList<Double> inputPlayerBattingList(ArrayList<String> playerNameList) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> playerBattingMoneyList = new ArrayList<Double>();

        for (String name : playerNameList) {
            System.out.println(name + "의 배팅 금액은?");
            playerBattingMoneyList.add(sc.nextDouble());
        }

        return playerBattingMoneyList;
    }

    public void printCardListOfGameParticipant(GameParticipant participant) {
        System.out.println(participant.getName() + participant.getCards().toString());
    }

    public boolean askNeedMoreCard(GameParticipant p){
        if(p.isBust()){
            return false;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("카드를 더 받으시겠습니까?");
        return sc.next() == "y";
    }



}
