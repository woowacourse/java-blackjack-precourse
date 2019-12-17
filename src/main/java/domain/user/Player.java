package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
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

    // TODO 추가 기능 구현

    /**
     * 플레이어의 이름을 리턴하는 getter
     *
     * @return 플레이어의 이름을 리턴
     */
    public String getName() {
        return this.name;
    }

    /**
     * 플레이어가 갖은 카드List를 리턴하는 getter
     *
     * @return 플레이어가 갖은 카드List
     */
    public String toStringCard() {
        List<String> cardInfo = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            cardInfo.add(cards.get(i).toString());
        }

        return this.name +
                "카드: " +
                String.join(",", cardInfo);
    }

    /**
     * 플레이어가 갖고있는 symbol의 합을 계산
     *
     * @return 총 symbol
     */
    public int calculateSymbol() {
        int symbol = 0;

        for (Card card : cards) {
            symbol += card.getSymbol();
        }
        return symbol;
    }
}