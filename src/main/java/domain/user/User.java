package domain.user;

import domain.Game;
import domain.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class User {
    protected ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getMinimumSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getSymbolValue();
        }
        return sum;
    }

    public ArrayList<Card> copyCardList() {
        ArrayList<Card> copyOfCards = new ArrayList<>();

        for (Card card : cards) {
            copyOfCards.add(card);
        }
        return copyOfCards;
    }

    public boolean isThereAce() {
        ArrayList<Card> copyOfCards = copyCardList();
        int cardsSize = copyOfCards.size();

        copyOfCards.removeIf(i -> i.getSymbolValue() == 1);
        if (cardsSize != copyOfCards.size()) {
            return true;
        }
        return false;
    }

    public int getAceSum() {
        int sum = getMinimumSum();

        if (isThereAce() && sum <= 11) {
            return getMinimumSum() + 10;
        }
        return getMinimumSum();
    }

    public boolean isBlackJack(){
        if(getAceSum() == 21){
            return true;
        }
        return false;
    }

    public abstract void printNameAndCards();

    public abstract void printNameAndCardsAndSum();
}
