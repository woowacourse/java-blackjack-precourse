package io;

import domain.user.Player;
import domain.user.Players;
import dto.Result;

import java.util.List;
import java.util.stream.Collectors;

import static io.InputView.DELIMITER;

public class OutputView {

    public static void showInitialDraw( Players players ) {
        String playersName = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println("딜러와" + playersName + "에게 2장의 카드를 나누었습니다.");
        showDealerInitialCard(players.getDealer());
        for (Player player : players.getPlayers()) {
            showPlayersCard(player);
        }
    }

    private static void showDealerInitialCard( Player dealer ) {
        String oneCard = dealer.getCardToString()
                .split(DELIMITER)[0];
        System.out.println(dealer.getName() + ": " + oneCard);
    }

    private static void showPlayersCard( Player player ) {
        System.out.println(player.getName() + ": " + player.getCardToString());
    }

    public static void showBlackJack() {
        System.out.println("BlackJack 입니다.");
    }

    public static void showBust() {
        System.out.println("Bust! 베팅 금액을 모두 잃습니다.");
    }

    public static void showDealerDraw() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void showResult( Players players ) {
        System.out.println("============Game End============");
        showResultValue(players.getDealer());
        players.getPlayers().
                forEach(player -> showResultValue(player));
        System.out.println();
    }

    private static void showResultValue( Player player ) {
        System.out.println(player.getName() + ": " + player.getCardToString() + "- 결과: " + player.getScore());
    }

    public static void showReusltsDetail( List<Result> results ) {
        System.out.println("## 최종 수익");
        for (Result result : results) {
            System.out.println(result.getResult());
        }
    }
}

