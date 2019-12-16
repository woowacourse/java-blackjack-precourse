package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private static final int MINIMUM_LENGTH_OF_PLAYER_NAME = 1;
    private static final int MINIMUM_BETTING_SIZE = 0;

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        validateName(name);
        validateBettingMoney(bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validateName(String name) {
        if (name.length() < MINIMUM_LENGTH_OF_PLAYER_NAME) {
            throw new IllegalArgumentException("각 플레이어의 이름은 1자 이상만 가능합니다.");
        }
    }

    private void validateBettingMoney(double bettingMoney) {
        if (bettingMoney <= MINIMUM_BETTING_SIZE) {
            throw new IllegalArgumentException("베팅 금액은 0을 초과해야 합니다.");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean hasAce() {
        return this.cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public String getCardInfo() {
        String result =  this.name + "의 카드: ";
        for (Card card : this.cards) {
            result += card.getCardInfo();
            result += " ";
        }
        return result;
    }

    public String getName() {
        return this.name;
    }
}
