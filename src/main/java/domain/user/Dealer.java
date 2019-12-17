package domain.user;

import domain.card.Card;
import domain.card.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    public static final String MESSAGE_DEALER_GET_MORE_CARD = "딜러는 16 이하라 카드를 한 장 더 받았습니다.";
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void printCards() {
        System.out.println("딜러: " + printInitialCardValue());
    }

    public String printInitialCardValue() {
        ArrayList<String> CardValue = getCardValue();
        return CardValue.get(0);
    }

    public void proceed(Stack stack) {
        while (validateUnder(17)) {
            addCard(stack.popCard());
            System.out.println(MESSAGE_DEALER_GET_MORE_CARD);
        }

    }

    public void showResult() {
        System.out.println("딜러 카드: " + printCardValue() + " - 결과: " + getRealScore());
    }

    public void showProfit() {
    }

    public void printProfit(int playersProfitSum) {
        System.out.println("딜러: " + playersProfitSum * -1);
    }
}
