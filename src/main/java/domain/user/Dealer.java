package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.view.InputUtil;
import domain.view.OutputUtil;

import java.util.ArrayList;
import java.util.List;

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

    private boolean checkAddLimitExcess() {
        int sum = super.getCards().stream()
                .filter(card -> card.getSymbol() != Symbol.ACE)
                .map(card -> card.getSymbol().getScore())
                .reduce(Integer::sum)
                .get();
        if (checkAce()) {
            OutputUtil.printAceScoreQuestion();
            sum += InputUtil.inputAceUse();
        }
        return sum > ADD_CARD_LIMIT;
    }

    public void doCheckAddLimitExcessAndFollowAction() {
        if (checkAddLimitExcess() == false) {
            OutputUtil.printDealerAddCard();
            addRandomCard();
        }
    }

    public void printFirstCard() {
        System.out.println("딜러:" + getFirstCard());
    }

    @Override
    public void printUserInfo() {
        System.out.println("딜러:" + printCards());
    }
}
