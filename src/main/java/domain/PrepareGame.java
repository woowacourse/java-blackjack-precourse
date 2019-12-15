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

    }

    private List<String> getPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        Scanner s = new Scanner(System.in);
        String name = s.next();

        List<String> names = Arrays.asList(name.split(","));
        return names;
    }
}
