package domain.user;

import view.InputUtil;
import view.OutputUtil;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final BettingMoney bettingMoney;

    Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    // TODO 추가 기능 구현
    void startAddCardLoop() {
        while (!checkExcess()) {
            boolean addCardFlag = inputAddCardQuestion();
            if (addCardFlag) {
                addRandomCard();
                printUserInfo();
            }
            if (!addCardFlag) {
                break;
            }
        }
    }

    private boolean inputAddCardQuestion() {
        OutputUtil.printAddCardQuestion(name);
        return InputUtil.inputAddCardQuestion();
    }

    boolean isWinBy(int dealerScore) {
        return dealerScore < calcurateScore();
    }

    boolean isDraw(int dealerScore) {
        return dealerScore == calcurateScore();
    }

    public String getName() {
        return name;
    }

    public double calcurateBlackJackBenefit(
            boolean isDealerBlackJack,
            boolean isPlayerBlackJack
    ) {
        return bettingMoney
                .calcurateBlackJackBenefit(isDealerBlackJack, isPlayerBlackJack);
    }

    double calcureateBenefit(boolean isWin) {
        return bettingMoney.calcureateBenefit(isWin);
    }

    double calcureateDrawBenefit() {
        return bettingMoney.calcureateDrawBenefit();
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
