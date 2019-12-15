package domain.user;

import domain.card.Card;
import domain.card.CardsOnGame;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private static final int ACE_CARD_BONUS_SCORE = 10;
    private static final int BLACK_JACK = 21;
    private static final int BURST = 22;
    private static final int AFTER_BURST_SCORE = 0;
    private static final int INIT_CARDS_QUANTITY = 2;

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

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public int getCountedScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .filter(x -> isBurst(x))
                .orElse(AFTER_BURST_SCORE);
    }

    public int getCountedScoreWithAceBonus() {
        return Optional.of(getCountedScore() + ACE_CARD_BONUS_SCORE)
                .filter(x -> isPlayerHaveAce())
                .filter(x -> isBurst(x))
                .orElse(getCountedScore());
    }

    public boolean isPlayerHaveAce() {
        return cards.stream().anyMatch(Card::checkAce);
    }

    public boolean isBurst(int score) {
        return  score < BURST;
    }

    public boolean isBlackJack() {
        return cards.size() == INIT_CARDS_QUANTITY
                && getCountedScoreWithAceBonus() == BLACK_JACK;
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public void initCardShare(CardsOnGame cards) {
        if (!isDealer()){
            addCard(cards.pickUpCard());
        }
        addCard(cards.pickUpCard());
    }

    @Override
    public String toString() {
        return name + "카드: "
                + cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }
}
