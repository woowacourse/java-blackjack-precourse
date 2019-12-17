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
            symbolSum = calculateCase(card, symbolSum);
        }
        return symbolSum;
    }

    /**
     * Ace일 경우 버스트가 나지않는 이상 11로 계산
     *
     * @param symbolSum 에이스 나오기 전까지 심볼의 합
     * @return 버스트되면 aceLittleNumber 아니면 aceBigNumber
     */
    public int calculateAutoAce(int symbolSum) {
        final int blackjack = 21;
        final int aceBigNumber = 11;
        final int aceLittleNumber = 1;

        if (aceBigNumber + symbolSum > blackjack) {
            return aceLittleNumber;
        }
        return aceBigNumber;
    }

    /**
     * ace 가 나올경우 나오지 않을경우 케이스를 나누는 메소드
     *
     * @param cards     카드 정보객체
     * @param symbolSum 카드의 심볼의 합
     * @return 심볼의 합
     */
    public int calculateCase(Card cards, int symbolSum) {
        final int ace = 1;
        if (cards.getSymbol() == ace) {
            symbolSum += calculateAutoAce(symbolSum);
            return symbolSum;
        }
        symbolSum += cards.getSymbol();
        return symbolSum;
    }


    /**
     * 딜러가 갖은 카드List를 리턴하는 getter
     *
     * @return 딜러가 갖은 카드List
     */
    public String toStringCard() {
        List<String> cardInfo = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            cardInfo.add(cards.get(i).toString());
        }

        return "딜러: " +
                String.join(",", cardInfo);
    }

    /**
     * 첫 시작할때 딜러는 첫카드를 제외하고 오픈
     *
     * @return 딜러가 받은 두번째 카드
     */
    public String startCardOpen() {
        final int secondIndex = 1;
        return "딜러: " + cards.get(secondIndex);

    }
}
