package domain.card;

import java.util.Objects;
import java.util.Scanner;

/**
 * 카드 한장을 의미하는 객체
 */
public class Card {
    private final Symbol symbol;
    private final Type type;
    private final String ACE_CHOICE_NUMBER_ONE = "1";
    private final String ACE_CHOICE_NUMBER_TWO = "11";
    private final int Dealer_ACE_NUMBER_BASE = 6;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public String cardInfo() {
        StringBuilder cardInfo = new StringBuilder();
        cardInfo.append(symbol);
        cardInfo.append("-");
        cardInfo.append(type);
        return cardInfo.toString();
    }

    public int cardNumber(String user, int sumNumbers) {
        int cardNumber = 0;
        cardNumber = symbol.getScore();
        if (cardNumber == 1 && user.equals("Player")) {
            cardNumber = selectAceNumberPlayer();
        } else if (cardNumber == 1 && user.equals("Dealer")) {
            cardNumber = selectAceNumberDealer(sumNumbers);
        }
        return cardNumber;
    }

    private int selectAceNumberDealer(int sumNumbers) {
        int aceNumber = 1;
        if (sumNumbers > Dealer_ACE_NUMBER_BASE) {
            aceNumber = 11;
        }
        return aceNumber;
    }

    private int selectAceNumberPlayer() {
        int aceNumber = 0;
        String aceNumberString = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Ace 카드를 어떤 수로 사용하시겠습니까? (1 또는 11만 입력 가능)");
            aceNumberString = scanner.nextLine().trim();
        } while (!selectAceNumberChecked(aceNumberString));
        aceNumber = Integer.parseInt(aceNumberString);
        scanner.close();
        return aceNumber;
    }

    private boolean selectAceNumberChecked(String aceNumberString) {
        if (aceNumberString.equals(ACE_CHOICE_NUMBER_ONE) || aceNumberString.equals(ACE_CHOICE_NUMBER_TWO)) {
            return true;
        }
        System.out.println("1 또는 11만 입력 가능합니다");
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    @Override
    public String toString() {
        return "Card{" +
                "symbol=" + symbol +
                ", type=" + type +
                '}';
    }
}
