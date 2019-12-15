package view;

import domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printDistributeMessage(List<Player> playerList) {
        StringBuilder sb = new StringBuilder();
        List<String> playerNameList = playerList.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());

        sb.append("딜러와 ");
        sb.append(String.join(", ", playerNameList));
        sb.append("에게 " + playerList.size() + "장의 카드를 나누었습니다.");
        System.out.print(sb);
    }
}
