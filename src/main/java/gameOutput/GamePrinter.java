package gameOutput;

import com.sun.deploy.util.StringUtils;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class GamePrinter {

    private static final String STATUS_FORMATTED_STRING = "%s 카드: %s";
    private static final String RESULT_FORMATTED_STRING = "%s 카드: %s - 결과: %d";
    private static final int DEALER_OPEN_CARD_INDEX = 0;

    public static void printStatus(Dealer dealer, List<Player> players) {
        List<String> names = players.stream().map(Player::getName).collect(Collectors.toList());
        String name = StringUtils.join(names, ", ");
        System.out.println(String.format("딜러와 %s에게 2장씩 카드를 나누었습니다.", name));
        System.out.println(makeDealerStatusStatement(dealer));
        players.forEach(GamePrinter::printPlayerStatus);
    }

    public static void printPlayerStatus(Player player) {
        System.out.println(makePlayerStatusStatement(player));
    }

    private static String makePlayerStatusStatement(Player player) {
        List<String> cardNames = player.getCards().stream().map(Card::getName).collect(Collectors.toList());
        String cardsName = StringUtils.join(cardNames, ", ");
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
        String cardsName = StringUtils.join(cardNames, ", ");
        return String.format(RESULT_FORMATTED_STRING, player.getName(), cardsName, player.calculateSum());
    }

    private static String makeDealerResultStatement(Dealer dealer) {
        List<String> cardNames = dealer.getCards().stream().map(Card::getName).collect(Collectors.toList());
        String cardsName = StringUtils.join(cardNames, ", ");
        return String.format(RESULT_FORMATTED_STRING, dealer.getName(), cardsName, dealer.calculateSum());

    }

}
