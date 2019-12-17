package gameOutput;

import com.sun.deploy.util.StringUtils;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import game.ProfitDecider;

import java.util.List;
import java.util.stream.Collectors;

public class GamePrinter {

    private static final String HANDOUT_FORMATTED_STRING = "%s와 %s에게 2장씩 카드를 나누었습니다.";
    private static final String STATUS_FORMATTED_STRING = "%s 카드: %s";
    private static final String RESULT_FORMATTED_STRING = "%s 카드: %s - 결과: %d";
    private static final String PROFIT_FORMATTED_STRING = "%s 수익: %.0f";
    private static final String PROFIT_STATEMENT = "최종 수익";
    private static final String MULTI_ELEMENT_DELEMITER = ", ";
    private static final int DEALER_OPEN_CARD_INDEX = 0;

    public static void printStatus(Dealer dealer, List<Player> players) {
        List<String> names = players.stream().map(Player::getName).collect(Collectors.toList());
        String name = StringUtils.join(names, MULTI_ELEMENT_DELEMITER);
        System.out.println(String.format(HANDOUT_FORMATTED_STRING, dealer.getName(), name));
        System.out.println(makeDealerStatusStatement(dealer));
        players.forEach(GamePrinter::printPlayerStatus);
    }

    public static void printPlayerStatus(Player player) {
        System.out.println(makePlayerStatusStatement(player));
    }

    private static String makePlayerStatusStatement(Player player) {
        List<String> cardNames = player.getCards().stream().map(Card::getName).collect(Collectors.toList());
        String cardsName = StringUtils.join(cardNames, MULTI_ELEMENT_DELEMITER);
        return String.format(STATUS_FORMATTED_STRING, player.getName(), cardsName);
    }

    private static String makeDealerStatusStatement(Dealer dealer) {
        List<Card> cards = dealer.getCards();
        Card openCard = cards.get(DEALER_OPEN_CARD_INDEX);
        return String.format(STATUS_FORMATTED_STRING, dealer.getName(), openCard.getName());
    }

    public static void printResult(Dealer dealer, List<Player> players) {
        System.out.println(makeDealerResultStatement(dealer));
        players.forEach(player -> System.out.println(makePlayerResultStatement(player)));
    }

    private static String makePlayerResultStatement(Player player) {
        List<String> cardNames = player.getCards().stream().map(Card::getName).collect(Collectors.toList());
        String cardsName = StringUtils.join(cardNames, MULTI_ELEMENT_DELEMITER);
        return String.format(RESULT_FORMATTED_STRING, player.getName(), cardsName, player.calculateSum());
    }

    private static String makeDealerResultStatement(Dealer dealer) {
        List<String> cardNames = dealer.getCards().stream().map(Card::getName).collect(Collectors.toList());
        String cardsName = StringUtils.join(cardNames, MULTI_ELEMENT_DELEMITER);
        return String.format(RESULT_FORMATTED_STRING, dealer.getName(), cardsName, dealer.calculateSum());
    }

    public static void printProfitResult(Dealer dealer, List<Player> players) {
        System.out.println(PROFIT_STATEMENT);
        printDealerProfit(dealer, players);
        players.forEach(player -> printPlayerProfit(dealer, player));
    }

    private static void printDealerProfit(Dealer dealer, List<Player> players) {
        System.out.println(makeDealerProfit(dealer, players));
    }

    private static String makeDealerProfit(Dealer dealer, List<Player> players) {
        double dealerProfit = ProfitDecider.calculateDealerProfit(dealer, players);
        return String.format(PROFIT_FORMATTED_STRING, dealer.getName(), dealerProfit);
    }

    private static void printPlayerProfit(Dealer dealer, Player player) {
        System.out.println(makePlayerProfit(dealer, player));
    }

    private static String makePlayerProfit(Dealer dealer, Player player) {
        double playerProfit = ProfitDecider.calculatePlayerProfit(dealer, player);
        return String.format(PROFIT_FORMATTED_STRING, player.getName(), playerProfit);
    }

}
