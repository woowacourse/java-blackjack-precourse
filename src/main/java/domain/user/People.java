package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import domain.card.Card;

public class People {
    static final int ZERO = 0;
    static final int BLACKJACK_CARDS = 2;
    static final int ONE = 1;
    static final int TEN = 10;
    static final int ELEVEN = 11;
    static final int BLACKJACK_NUMBER = 21;

    private final List<Card> cards = new ArrayList<>();

    public People() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotalNumber() {
        ArrayList<Integer> numberList = new ArrayList<>();
        for (Card card : cards) {
            numberList.add(card.getSymbol().getScore());
        }
        return calculateNumber(numberList);
    }

    public int cardCount() {
        return cards.size();
    }

    public String getCardList() {
        return cards.stream().map((x) -> {
            return x.getCardString();
        }).collect(Collectors.joining(", "));
    }

    public boolean isBlackJack() {
        if ((cards.size() == BLACKJACK_CARDS) && blackJackCheck())
            return true;
        return false;
    }

    private int calculateNumber(ArrayList<Integer> numberList) {
        int aceCount = ZERO;
        int result = ZERO;
        for (Integer number : numberList) {
            aceCount += isAce(number.intValue());
            result += number.intValue();
        }
        return getMaximumNumber(aceCount, result);
    }

    private int getMaximumNumber(int aceCount, int result) {
        for (int i = 0; i < aceCount; i++) {
            result = addCalculateBurst(result, TEN);
        }
        return result;
    }

    private int addCalculateBurst(int result, int ten) {
        if (result + ten <= BLACKJACK_NUMBER) {
            return result + ten;
        }
        return result;
    }

    private int isAce(int number) {
        if (number == ONE)
            return ONE;
        return ZERO;
    }

    private boolean blackJackCheck() {
        if (((cards.get(0).getSymbol().getScore() + cards.get(1).getSymbol().getScore()) == ELEVEN)
                && ((cards.get(0).getSymbol().getScore() == ONE) || (cards.get(0).getSymbol().getScore() == TEN)))
            return true;
        return false;
    }
}