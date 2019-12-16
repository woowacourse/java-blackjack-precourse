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
        boolean stopFlag = false;
        while (checkExcess() == false && stopFlag == false) {
            OutputUtil.printAddCardQuestion(name);
            doQuestionFollowAction(stopFlag);
        }
    }

    public void doQuestionFollowAction(boolean stopFlag) {
        boolean addCardFlag = InputUtil.inputAddCardQuestion();
        if (addCardFlag) {
            addRandomCard();
        }
        if (addCardFlag == false) {
            stopFlag = true;
        }
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
}
