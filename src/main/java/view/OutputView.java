package view;

import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Players;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String JOIN_DELIMITER = ", ";
    private static final String CARD_STATUS_FORMAT = "%s 카드 : %s";

    public static void showStartStatus(Dealer dealer, Players players) {
        String names = String.join(JOIN_DELIMITER, players.getNames());
        System.out.println("딜러와 " + names + "에게 2장의 카드를 나누었습니다.\n");

        List<Gamer> gamers = collectGamer(dealer, players);

        showAllGamerStatus(gamers);
    }

    private static List<Gamer> collectGamer(Dealer dealer, Players players) {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(players.getPlayers());
        return gamers;
    }

    private static void showAllGamerStatus(List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            showGamerStatus(gamer);
        }
        System.out.println();
    }

    public static void showGamerStatus(Gamer gamer) {
        System.out.println(makeGamerStatus(gamer));
    }

    private static String makeGamerStatus(Gamer gamer) {
        String name = gamer.getName();
        String cardStatus = CardStatus.getStatus(gamer.getCards());
        return String.format(CARD_STATUS_FORMAT, name, cardStatus);
    }

    public static void showDealerCanReceive(boolean canReceive) {
        if (canReceive) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.\n");
            return;
        }
        System.out.println("딜러는 17이상이라 카드를 받지 않습니다.\n");
    }

    public static void showLastStatus(List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            String status = makeGamerStatus(gamer);
            int score = gamer.getScore();
            System.out.println(status + " - 결과 : " + score);
        }
    }

    public static void showResult() {
        System.out.println("## 최종 수익");
    }
}
