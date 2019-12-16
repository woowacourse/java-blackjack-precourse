package domain;

import domain.user.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 게임을 준비하는 객체
 */
public class PrepareGame {

    private List<Player> players;

    /**
     * 게임 준비를 담당하는 함수
     */
    public void prepare() {
        List<String> names = getPlayerName();
        List<Double> bettingMoneys = getBettingMoneys(names);
        createPlayerInstance(names, bettingMoneys);
    }

    private List<String> getPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        Scanner s = new Scanner(System.in);
        String name = s.next();
        while (!checkNameInputValid(name)) {
            name = s.next();
        }
        List<String> names = Arrays.asList(name.split(","));
        return names;
    }

    private boolean checkNameInputValid(String name) {
        if (name.contains(",,")) {
            System.out.println("쉼표를 하나만 작성해주세요.");
            return false;
        }
        return true;
    }

    private List<Double> getBettingMoneys(List<String> names) {
        List<Double> bettingMoneys = new ArrayList<Double>();
        Scanner s = new Scanner(System.in);
        for (int i = 0 ; i < names.size() ; i++) {
            System.out.println(names.get(i) + "의 배팅 금액은?");
            double money = s.nextDouble();
            bettingMoneys.add(money);
        }
        return bettingMoneys;
    }

    /**
     * 입력받은 이름과 배팅 금액으로 player 객체들을 생성한다.
     */
    private void createPlayerInstance(List<String> names, List<Double> bettingMoneys) {
        players = new ArrayList<>();
        for (int i = 0 ; i < names.size() ; i++) {
            players.add(new Player(names.get(i), bettingMoneys.get(i)));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
