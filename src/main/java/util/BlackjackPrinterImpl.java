package util;

import domain.BlackjackPrinter;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackPrinterImpl implements BlackjackPrinter {

    private StringBuilder stringBuilder;
    BlackjackPrinterImpl() {
        stringBuilder = new StringBuilder();
    }

    @Override
    public void printUserState(User user) {
        stringBuilder.append(user);
        stringBuilder.append("카드: ");

        List<String> cardNames = user.getCards().stream().map(Card::toString).collect(Collectors.toList());
        if (user instanceof Dealer) {
            cardNames.retainAll(Collections.singletonList(cardNames.get(0)));
        }
        stringBuilder.append(String.join(", ", cardNames));
        System.out.println(stringBuilder);
    }

    @Override
    public void printResult(Dealer dealer, List<Player> players) {

    }
}
