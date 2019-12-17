package domain.user;

import domain.card.Card;
import domain.card.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private static final String MESSAGE_DEALER_GET_MORE_CARD = "딜러는 16 이하라 카드를 한 장 더 받았습니다.";
    private static final String PRINT_CARD_HEAD = "딜러: ";
    private static final int FIRST_CARD_INDEX = 0;
    private static final int DEALER_HIT_CEILING = 17;
    private static final String PRINT_RESULT_HEAD = "딜러 카드: ";
    private static final String PRINT_RESULT_SCORE_HEAD = " - 결과: ";
    private static final String PRINT_PROFIT_HEAD = "딜러: ";
    private static final int DEALER_PROFIT_FACTOR = -1;
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void printCards() {
        System.out.println(PRINT_CARD_HEAD + printInitialCardValue());
    }

    private String printInitialCardValue() {
        ArrayList<String> CardValue = getCardValue();
        return CardValue.get(FIRST_CARD_INDEX);
    }

    void proceed(Stack stack) {
        while (validateUnder(DEALER_HIT_CEILING)) {
            addCard(stack.popCard());
            System.out.println(MESSAGE_DEALER_GET_MORE_CARD);
        }

    }

    void showResult() {
        System.out.println(PRINT_RESULT_HEAD + printCardValue() + PRINT_RESULT_SCORE_HEAD + getRealScore());
    }

    void printProfit(int playersProfitSum) {
        System.out.println(PRINT_PROFIT_HEAD + playersProfitSum * DEALER_PROFIT_FACTOR);
    }
}
