package domain.user;

import com.sun.deploy.util.StringUtils;
import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private static final String FORMATTED_STRING = "%s카드: %s - 결과: %d";
    private static final int BLACK_JACK = 21;
    private static final int MULTIPLIED_LIST_SIZE_BASIS = 2;

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateSum() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    public String makeStatusStatement() {
        List<String> cardNames = cards.stream().map(Card::getName).collect(Collectors.toList());
        String cardsName = StringUtils.join(cardNames, ", ");
        return String.format(FORMATTED_STRING, name, cardsName, calculateSum());
    }

    public boolean isFirstTwoBlackJack() {
        return cards.size() == MULTIPLIED_LIST_SIZE_BASIS && calculateSum() == BLACK_JACK;
    }
}
