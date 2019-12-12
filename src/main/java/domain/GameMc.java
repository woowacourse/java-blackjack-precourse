package domain;

import domain.user.Player;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMc {
    private ArrayList<Player> players = new ArrayList<Player>();
    private String nameInput;

    public void gameMc() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        nameInput = Input();
        for (String name : nameInput.split(",")) {
            System.out.println(name + "의 베팅 금액은?");
            players.add(new Player(name, bettingMoneyInput()));
        }
    }

    public String Input() {
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    public int bettingMoneyInput() {
        int money = Integer.valueOf(Input());
        while (money <= 0) {
            System.out.println("0보다 큰 수를 입력하세요");
            money = Integer.valueOf(Input());
        }
        return money;
    }
}
