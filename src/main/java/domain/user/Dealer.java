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
    public int calculateSymbol() {
        int symbolSum = 0;

        for (Card card : cards) {
            symbolSum = calculateCase(card,symbolSum);
        }
        return symbolSum;
    }

    /**
     * Ace일 경우 버스트가 나지않는 이상 11로 계산
     * @param cards card 객체
     * @param symbolSum 에이스 나오기 전까지 심볼의 합
     * @return 버스트되면 1 아니면 11
     */
    public int calculateAutoAce(Card cards, int symbolSum) {
        final int blackjack = 21;

        if (cards.getSymbol() + symbolSum > blackjack) {
            return 1;
        }
        return 11;
    }

    /**
     * ace 가 나올경우 나오지 않을경우 케이스를 나누는 메소드
     *
     * @param cards 카드 정보객체
     * @param symbolSum 카드의 심볼의 합
     * @return 심볼의 합
     */
    public int calculateCase(Card cards, int symbolSum) {
        if(cards.getSymbol()==1){
            symbolSum += calculateAutoAce(cards, symbolSum);
            return symbolSum;
        }
        symbolSum += cards.getSymbol();
        return symbolSum;
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

    public List<Card> getCards() {
        return cards;
    }
}
