package domain.user;

import view.InputUtil;
import view.OutputUtil;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private static final String DOUBLE_COLON = " : ";

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
                printUserState();
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
        return name + DOUBLE_COLON + bettingMoney;
    }

    @Override
    public void printUserState() {
        OutputUtil.printPlayerState(name, printCards());
    }

    @Override
    public void printFinalOutput() {
        OutputUtil.printPlayerState(name, printCards(), calcurateScore());
    }
}
