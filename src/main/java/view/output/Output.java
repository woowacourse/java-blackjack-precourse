package view.output;

import domain.user.Player;
import domain.user.Table;

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
        System.out.println("딜러와 " + names + "에게 2장의 카드를 나누었습니다.");
    }

    public void showMessageHavingCard(List<Player> players) {
        players.forEach(player
                -> System.out.println(player.getName()
                + "카드: "
                + player.getCards()));
    }

    public void showMessageOneMoreCard(String name) {
        System.out.println(name + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void showMessageDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void showMessageResult(List<Player> players) {
        players.forEach(player
                -> System.out.println(player.getName()
                + "카드: "
                + player.getCards()
                + "= 결과: "
                + player.calculateScore()));
    }

    public void showMessageResultMoney(Table table) {
        System.out.println("## 최종 수익");
        List<Double> balances = table.calculateMoney();

        for (int i = 0; i < table.getTable().size(); i++) {
            System.out.println(table.getTable().get(i).getName() + ": " + balances.get(i));
        }
    }
}
