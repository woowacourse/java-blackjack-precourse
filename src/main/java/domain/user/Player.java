package domain.user;

import domain.outcome.BenefitType;
import view.InputUtil;
import view.OutputUtil;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현
    public void startAddCardLoop() {
        while (checkExcess() == false) {
            boolean addCardFlag = inputAddCardQuestion();
            if (addCardFlag) {
                addRandomCard();
                printUserInfo();
            }
            if (addCardFlag == false) {
                break;
            }
        }
    }

    private boolean inputAddCardQuestion() {
        OutputUtil.printAddCardQuestion(name);
        return InputUtil.inputAddCardQuestion();
    }

    public boolean isWinBy(int dealerScore) {
        return dealerScore < calcurateScore();
    }

    public String getName() {
        return name;
    }

    public double calcurateBenefit(BenefitType benefitType) {
        return bettingMoney * benefitType.getMultipleValue();
    }

    public double calcurateBlackJackBenefit(boolean isDealerBlackJack, boolean isPlayerBlackJack) {
        return BenefitType
                .calcurateBlackJackBenefit(isDealerBlackJack, isPlayerBlackJack)
                .getMultipleValue()
                * bettingMoney;
    }

    public double calcureateBenefit(boolean isWin) {
        return BenefitType.calcureateWinnerBenefit(isWin).getMultipleValue()
                * bettingMoney;
    }

    @Override
    public String toString() {
        return name + " : " + bettingMoney;
    }

    @Override
    public void printUserInfo() {
        System.out.println(name + "카드:" + printCards());
    }

    @Override
    public void printFinalOutput() {
        System.out.println(name +
                "카드:"
                + printCards() +
                " - 결과: "
                + calcurateScore());
    }
}
