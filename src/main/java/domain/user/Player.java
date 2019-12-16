package domain.user;

import domain.BenefitMultipleType;
import domain.view.InputUtil;
import domain.view.OutputUtil;

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
        boolean addCardFlag = InputUtil.inputAddCardQuestion();

        return addCardFlag;
    }

    public boolean isWinBy(int dealerScore) {
        return dealerScore < calcurateScore();
    }

    public String getName() {
        return name;
    }

    public double getBenefit(BenefitMultipleType benefitMultipleType) {
        return bettingMoney * benefitMultipleType.getMultipleValue();
    }

    @Override
    public String toString() {
        return name + " : " + bettingMoney;
    }

    @Override
    public void printUserInfo() {
        System.out.println(name + "카드:" + printCards());
    }
}
