package domain.user;

import domain.card.Card;
import domain.card.Stack;

import java.util.ArrayList;
import java.util.List;

import static view.inputView.inputHitOrStand;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private static final String CARD_NAME_VALUE_DELIMITER = ": ";
    private static final int BLACKJACK_CEILING = 21;
    private static final String RESPONSE_NO = "n";
    private static final String RESPONSE_INITIAL_VALUE = "";
    private static final String MESSAGE_BLACKJACK = "은(는) 블랙잭입니다!";
    private static final String RESPONSE_YES = "y";
    private static final String MESSAGE_INPUT_Y_OR_N = "y나 n으로 입력해주세요";
    private static final String PRINT_RESULT_HEAD = " 카드: ";
    private static final String PRINT_RESULT_SCORE_HEAD = " - 결과: ";
    private static final int BANKRUPT_FLOOR = 22;
    private static final int LOOSING_FACTOR = -1;
    private static final double BLACKJACK_FACTOR = 1.5;
    private static final int DRAW_PROFIT = 0;
    private static final String PRINT_PROFIT_DELIMITER = ": ";
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    String getName() {
        return this.name;
    }

    void printCards() {
        System.out.println(getName() + CARD_NAME_VALUE_DELIMITER + printCardValue());
    }

    void proceed(Stack stack) {
        String response = RESPONSE_INITIAL_VALUE;
        while (!isBlackjack() && (validateUnder(BLACKJACK_CEILING) && !response.equals(RESPONSE_NO))) {
            response = choice(stack);
        }
        printBlackjack(isBlackjack());
    }

    private void printBlackjack(boolean blackjack) {
        if (blackjack) {
            System.out.println(getName() + MESSAGE_BLACKJACK);
        }
    }

    private String choice(Stack stack) {
        String response;
        do {
            response = inputHitOrStand(getName());
        } while (!validateHitOrStandResponse(response, stack));
        return response;
    }

    private boolean validateHitOrStandResponse(String response, Stack stack) {
        if (response.equals(RESPONSE_YES) || response.equals(RESPONSE_NO)) {
            HitOrResponse(response, stack);
            return true;
        }
        System.out.println(MESSAGE_INPUT_Y_OR_N);
        return false;
    }

    private void HitOrResponse(String response, Stack stack) {
        if (response.equals(RESPONSE_YES)) {
            addCard(stack.popCard());
            printCards();
        }
    }

    void showResult() {
        System.out.println(getName() + PRINT_RESULT_HEAD + printCardValue() + PRINT_RESULT_SCORE_HEAD + getRealScore());
    }


    int compare(Dealer dealer) {
        if (!validateUnder(BANKRUPT_FLOOR) || getRealScore() < dealer.getRealScore()) {
            return (int) bettingMoney * LOOSING_FACTOR;
        }
        if ((validateUnder(BANKRUPT_FLOOR) && !dealer.validateUnder(BANKRUPT_FLOOR)) || getRealScore() > dealer.getRealScore()) {
            return (int) bettingMoney;
        }
        if (isBlackjack()) {
            return (int) (bettingMoney * BLACKJACK_FACTOR);
        }
        return DRAW_PROFIT;
    }

    void printProfit(int profit) {
        System.out.println(getName() + PRINT_PROFIT_DELIMITER + profit);
    }

}
