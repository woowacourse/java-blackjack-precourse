package view.output;

import domain.user.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Output {
    public void showMessageInputName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리");
    }

    public void showMessageInputMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");
    }

    public void showMessageDispensing(List<Player> players) {
        players.remove(0);

        String names = players.stream()
                .collect(Collectors.groupingBy(Player::getName))
                .keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(", "));
        System.out.println("딜러와 " + names +"에게 2장의 카드를 나누었습니다.");
    }
}
