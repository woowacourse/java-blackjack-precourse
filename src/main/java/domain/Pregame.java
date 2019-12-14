package domain;

import domain.user.Player;

import java.util.Scanner;

public class Pregame {
    Scanner sc = new Scanner(System.in);
    String[] name;
    Player[] player;

    public void run() {
        inputPlayerName();
        player = new Player[name.length];
        createPlayer();
    }

    public void inputPlayerName(){
        String totalInputName;

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표 기준으로 구분) : ");
        totalInputName = sc.next();
        name = totalInputName.split(",");
    }

    public void createPlayer() {

    }
}
