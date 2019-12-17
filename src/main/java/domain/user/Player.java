package domain.user;

import com.sun.deploy.util.StringUtils;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {

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

    public boolean isBlackJack() {
        return isBlackJack(cards);
    }

    public boolean isSameScore(int score) {
        return calculateSum() == score;
    }

    public boolean isBursted() {
        return isBursted(cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
