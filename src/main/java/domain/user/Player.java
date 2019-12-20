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
        boolean addCardFlag = inputAddCardQuestion();
        while (calcurateScore().isBust() && addCardFlag) {
            addRandomCard();
            printUserState();
            addCardFlag = inputAddCardQuestion();
        }
    }

    private boolean inputAddCardQuestion() {
        OutputUtil.printAddCardQuestion(name);
        return InputUtil.inputAddCardQuestion();
    }

    public double getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }

    public String getName() {
        return name;
    }

    // TODO : 이익 계산 책임을 Benefit에게 넘겨버려보자.
    public double calcurateBlackJackBenefit(
            boolean isDealerBlackJack,
            boolean isPlayerBlackJack
    ) {
        return bettingMoney
                .calcurateBlackJackBenefit(isDealerBlackJack, isPlayerBlackJack);
    }

    // UI 로직 넘기기
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
        OutputUtil.printPlayerState(name, printCards(), calcurateScore().getScore());
    }
}
