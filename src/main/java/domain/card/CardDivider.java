package domain.card;

import java.util.ArrayList;
import java.util.List;

/**
 * 카드 세트를 들고 여기서 랜덤하게 한장 뽑아서 주는 객체
 */
public class CardDivider {
    private final int RANDOM_NUM_MULTIPLIER = 100;      /* (랜덤실수) x 100 */
    private final List<Card> cardList;
    private List<Card> usedCards = new ArrayList<Card>();

    public CardDivider(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Card getOneRandomCard() {
        Card ret;

        if (cardList == null || cardList.size() < 1) {
            return null;
        }

        /* 랜덤하게 뽑을 카드의 인덱스 결정 */
        ret = cardList.get((int)(Math.random() * RANDOM_NUM_MULTIPLIER) % cardList.size());

        for (int idx = 1; idx < cardList.size() && isAlreadyUsed(ret); idx++) {
            ret = cardList.get(idx);
        }

        return ret;
    }

    private boolean isAlreadyUsed(Card card) {
        boolean isUsed = false;

        for (int idx = 0; idx < usedCards.size() && !isUsed; idx++) {
            isUsed = usedCards.get(idx).equals(card);
        }

        return isUsed;
    }
}
