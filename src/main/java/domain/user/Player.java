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
        System.out.println(getName() + ": " + printCardValue());
    }

    void proceed(Stack stack) {
        String response = "";
        while (!isBlackjack() && (validateUnder(21) && !response.equals("n"))) {
            response = choice(stack);
        }
        printBlackjack(isBlackjack());
    }

    private void printBlackjack(boolean blackjack) {
        if (blackjack) {
            System.out.println(getName() + "은(는) 블랙잭입니다!");
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
        if (response.equals("y") || response.equals("n")) {
            HitOrResponse(response, stack);
            return true;
        }
        System.out.println("y나 n으로 입력해주세요");
        return false;
    }

    private void HitOrResponse(String response, Stack stack) {
        if (response.equals("y")) {
            addCard(stack.popCard());
            printCards();
        }
    }

    void showResult() {
        System.out.println(getName() + " 카드: " + printCardValue() + " - 결과: " + getRealScore());
    }


    int compare(Dealer dealer) {
        if (!validateUnder(22) || getRealScore() < dealer.getRealScore()) {
            return (int) bettingMoney * -1;
        }
        if ((validateUnder(22) && !dealer.validateUnder(22)) || getRealScore() > dealer.getRealScore()) {
            return (int) bettingMoney;
        }
        if (isBlackjack()) {
            return (int) (bettingMoney * 1.5);
        }
        return 0;
    }

    void printProfit(int profit) {
        System.out.println(getName() + ": " + profit);
    }

}
