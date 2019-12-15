package domain;

import domain.user.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PrepareGame {

    List<Player> players;

    public void prepare() {
        List<String> names = getPlayerName();
        List<Double> bettingMoneys = getBettingMoneys(names);
        createPlayerInstance(names, bettingMoneys);
    }

    private List<String> getPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        Scanner s = new Scanner(System.in);
        String name = s.next();

        List<String> names = Arrays.asList(name.split(","));
        return names;
    }

    private List<Double> getBettingMoneys(List<String> names) {
        List<Double> bettingMoneys = new ArrayList<Double>();
        Scanner s = new Scanner(System.in);
        for (int i = 0 ; i < names.size() ; i++) {
            System.out.println(names.get(i) + "의 배팅 금액은?");
            double money = s.nextInt();
            bettingMoneys.add(money);
        }
        return bettingMoneys;
    }

    private void createPlayerInstance(List<String> names, List<Double> bettingMoneys) {
        players = new ArrayList<Player>();
        for (int i = 0 ; i < names.size() ; i++) {
            players.add(new Player(names.get(i), bettingMoneys.get(i)));
        }
    }
}
