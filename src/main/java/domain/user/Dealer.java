package domain.user;

import view.OutputUtil;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private static final int ADD_CARD_LIMIT = 16;
    private static final int ZERO = 0;

    // TODO 추가 기능 구현
    private String getFirstCard() {
        return getCards().getCards().get(ZERO).toString();
    }

    private boolean checkAddLimitExcess() {
        return getCards().calcurateScore().isBigBy(ADD_CARD_LIMIT);
    }

    void hit() {
        while (!checkAddLimitExcess()) {
            OutputUtil.printDealerAddCard();
            addRandomCard();
        }
    }

    @Override
    public void printUserState() {
        OutputUtil.printDealerState(getFirstCard());
    }

    @Override
    public void printFinalOutput() {
        OutputUtil.printDealerFinalOutput(printCards(), getCards().calcurateScore().getScore());
    }
}
