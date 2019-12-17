package domain.ui;

import java.util.*;

public class Utill {

    private List<String> playerNameList = new ArrayList<String>();

    public List<String> initPlayerNameList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Collections.addAll(playerNameList, sc.next().split(","));
        return playerNameList;
    }

}
