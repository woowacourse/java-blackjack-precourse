package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private static final int INIT_CARD_SIZE = 2;
    private static final int PLAYER_MIN_BURST = 22;

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public boolean aceCardExist() {
        return cards.stream().anyMatch(Card::isAce);
    }
}
