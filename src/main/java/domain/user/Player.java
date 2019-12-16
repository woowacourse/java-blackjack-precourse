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

    public String getName() {
        return this.name;
    }

    public void printCards() {
        System.out.println(getName() + ": " + printCardValue() + getScore());
    }

    public void proceed(Stack stack) {
        String response;
        do {
            response = choice(stack);
        } while (!validateOver21() || !response.equals("n"));
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

}
