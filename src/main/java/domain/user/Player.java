package domain.user;

import domain.card.Card;
import domain.card.Deck;
import view.Input;
import view.Output;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private static final int INIT_CARD_SIZE = 2;
    private static final int PLAYER_MIN_BURST = 22;
    private static final int BLACK_JACK = 21;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BURST = 0;

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
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

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int getScoreWithAceCheck() {
        return Optional.of(getScore())
                .filter(x -> aceCardExist())
                .filter(x -> isNotBurstWithBonusScore())
                .map(this::aceBonusScore)
                .orElse(getScore());
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public boolean aceCardExist() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isNotBurst() {
        return getScoreWithAceCheck() < PLAYER_MIN_BURST;
    }

    private boolean isNotBurstWithBonusScore() {
        return getScore() + ACE_BONUS_SCORE < PLAYER_MIN_BURST;
    }

    private int aceBonusScore(int score) {
        return score + ACE_BONUS_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == INIT_CARD_SIZE && getScoreWithAceCheck() == BLACK_JACK;
    }

    public void initCard(Deck deck) {
        if (!isDealer()) {
            addCard(deck.drawCard());
        }
        addCard(deck.drawCard());
    }

    public void moreCard(Deck deck) {
        if (isNotBurst() && !isBlackJack() && new Input().asWantMoreCard(this.name)) {
            addCard(deck.drawCard());
            Output.showPlayerInfo(this);

            moreCard(deck);
        }
    }

    public int getResultScore() {
        return Optional.of(getScoreWithAceCheck())
                .filter(x -> isNotBurst())
                .orElse(BURST);
    }

    public boolean isHigherScoreThen(Player player) {
        return getResultScore() > player.getResultScore();
    }

    public boolean isSameScoreWith(Player player) {
        return getResultScore() == player.getResultScore();
    }

    @Override
    public String toString() {
        return name + "카드: " + cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }
}
