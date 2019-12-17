package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    /**
     * 딜러가 갖고있는 symbol의 합을 계산
     *
     * @return 총 symbol
     */
    public int calculateSymbol(){
        int symbol = 0;

        for(Card card: cards){
            symbol += card.getSymbol();
        }
        return symbol;
    }

    /**
     * 딜러가 갖은 카드List를 리턴하는 getter
     * 딜러의 카드는 첫카드를 제외하고 오픈된다.
     *
     * @return 플레이어가 갖은 카드List
     */
    public String toStringCard() {
        List<String> cardInfo = new ArrayList<>();

        for (int i = 1; i < cards.size(); i++) {
            cardInfo.add(cards.get(i).toString());
        }

        return "딜러: " +
                String.join(",", cardInfo);
    }
}
