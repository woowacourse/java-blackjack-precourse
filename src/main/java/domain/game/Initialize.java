package domain.game;

import domain.user.*;
import domain.card.*;

import java.util.*;

public class Initialize {
    Scanner sc = new Scanner(System.in);

    public ArrayList<String> setPlayerNames() {
        String input;
        ArrayList<String> playerNames = new ArrayList<String>();

        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표기준으로분리)");
        input = sc.nextLine();

        String[] names = input.split(",");
        for(String name : names) {
            playerNames.add(name);
        }
        return playerNames;
    }

    public double setPlayerBettings(String name) {
        double betting;

        System.out.println(name + "의 배팅 금액은?");
        betting = sc.nextDouble();

        if(betting <= 0) {
            System.out.println("입력이 잘못되었습니다.");
            setPlayerBettings(name);
        }

        return betting;
    }
}