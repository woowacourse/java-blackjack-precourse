package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String toString() {
        return name + " : " + cardsToString();
    }

    private String cardsToString() {
        List<Card> cards = getCards();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : cards) {
            stringJoiner.add(card.toString());
        }
        return stringJoiner.toString();
    }

    public double lose() {
        return -bettingMoney;
    }

    public double win() {
        return bettingMoney;
    }

    public double draw() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public boolean wantHit() {
        Scanner scanner = new Scanner(System.in);
        String answer;
        do {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
            answer = scanner.nextLine().toLowerCase();
        } while (!isValid(answer));
        return answer.equals("y");
    }

    public boolean isValid(String inputString) {
        if (inputString.equals("y") || inputString.equals("n")) {
            return true;
        }
        System.out.println("y 또는 n을 입력해주세요.");
        return false;
    }

    public double checkBenefit(int dealerScore) {
        int playerScore = calculateScore();
        int scoreLimit = 21;
        if (playerScore > scoreLimit || (dealerScore <= scoreLimit && playerScore < dealerScore)) {
            return lose();
        }
        if (dealerScore == playerScore) {
            return draw();
        }
        return win();
    }
}
