package view.output;

import domain.manager.Manual;
import domain.user.Player;
import domain.user.Table;
import view.input.Input;

import java.util.List;
import java.util.stream.Collectors;

public class Output {
    private static final String MONEY_IMG = "\uD83D\uDCB5";
    private Input input = new Input();

    public String showMessageInputName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return input.inputName();
    }

    public Double showMessageInputMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");

        return input.inputMoney();
    }

    public void showMessageDispensing(List<Player> players) {
        String names = String.join(", ", players.stream()
                .collect(Collectors.groupingBy(Player::getName))
                .keySet());
        System.out.println("딜러와 " + names + "에게 2장의 카드를 나누었습니다.");
    }

    public void showMessageHavingCard(List<Player> players) {
        players.forEach(player
                -> System.out.println(player.getName()
                + "카드: "
                + player.getCards()));
    }

    public void showMessageHavingCard(Player player) {
        System.out.println(player.getName() + "카드: " + player.getCards());
    }

    public boolean showMessageOneMore(String name) {
        System.out.println(name + "(은)는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return input.oneMoreCard(input.inputChoice());
    }

    public void showMessageBurst(String name) {
        System.out.println(name + "(은)는 Burst 하였습니다...");
    }

    public void showMessageMisInputErrorClose() {
        System.out.println("잘못 입력하였습니다.");
        System.out.println("아니오로 처리됩니다.");
    }

    public void showMessageMisInputErrorReturn() {
        System.out.println("잘못 입력하였습니다.");
        System.out.println("다시 입력하세요.");
    }

    public void showMessageCardEmptyClose() {
        System.out.println("카드를 모두 소모하였습니다.");
        System.out.println("게임을 종료합니다.");
    }

    public void showMessageTypeMismatching() {
        System.out.println("양의 숫자 타입만 입력 가능합니다.");
    }

    public void showMessageDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void showMessageResult(List<Player> players) {
        newLine();
        players.forEach(player
                -> System.out.println(player.getName()
                + "카드: "
                + player.getCards()
                + "= 결과: "
                + player.calculateScore()));
    }

    public void showMessageResultMoney(Table table) {
        System.out.println("## 최종 수익  " + MONEY_IMG);

        for (int i = Manual.DEALER_POSITION.getValue();
             i < table.getTable().size(); i++) {
            System.out.println(table.getTable().get(i).getName()
                    + ": "
                    + table.calculateMoney().get(i)
                    + showMessageBlackjack(table, i));
        }
    }

    private String showMessageBlackjack(Table table, int index) {
        if (table.getBlackjack().get(index)) {
            return "   " + MONEY_IMG + " 블랙잭!! " + MONEY_IMG;
        }

        return "";
    }

    public void newLine() {
        System.out.println();
    }
}
