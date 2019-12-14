package domain;

import domain.user.Player;

import java.util.Arrays;
import java.util.Scanner;

public class Pregame {
    Scanner sc = new Scanner(System.in);
    String[] name;
    Player[] player;
    double bettingMoney[];

    public void run() {
        inputPlayerName();
        player = new Player[name.length];
        inputBettingMoney();
        createPlayer();
    }

    public void inputPlayerName(){
        String totalInputName;

        System.out.println("참여할 플레이어의 이름을 입력하세요.(이름은 쉼표 기준으로 구분) : ");
        totalInputName = sc.next();
        name = totalInputName.split(",");
        if (!checkFormAboutName()) {
            inputPlayerName();
        }
    }

    private boolean checkFormAboutName() {
        if(name.length < 2 || name.length > 8) {
            System.out.println("player는 2~8명으로 설정해주세요.");
            return false;
        }
        return true;
    }

    private void inputBettingMoney() {
        bettingMoney = new double[name.length];
        for(int i = 0; i < name.length; i++) {
            System.out.println(name[i]+"의 배팅 금액은?");
            bettingMoney[i] = sc.nextDouble();
        }
    }

    public void createPlayer() {
        for (int i = 0; i < name.length; i++) {
            player[i] = new Player(name[i], bettingMoney[i]);
        }
    }
}
