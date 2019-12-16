package domain.user;

import domain.card.Symbol;
import domain.view.InputUtil;
import domain.view.OutputUtil;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private static final int ADD_CARD_LIMIT = 16;

    public Dealer() {
    }

    // TODO 추가 기능 구현
    public String getFirstCard() {
        super.checkCardsEmpty();
        return getCards().get(0).getCardInfo();
    }

    public boolean checkAddLimitExcess() {
        int sum = calcurateScore();
        return sum > ADD_CARD_LIMIT;
    }

    public void hit() {
        while (checkAddLimitExcess() == false) {
            OutputUtil.printDealerAddCard();
            addRandomCard();
        }
    }

    @Override
    public void printUserInfo() {
        System.out.println("딜러:" + getFirstCard());
    }

    @Override
    public void printFinalOutput() {
        System.out.println("딜러:" + printCards() + " - 결과: " + calcurateScore());
    }
}
