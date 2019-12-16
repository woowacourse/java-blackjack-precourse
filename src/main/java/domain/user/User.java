package domain.user;

import domain.card.Card;

import java.lang.reflect.Array;
import java.util.*;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public User() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<String> getCardValue() {
        ArrayList<String> cardInfo = new ArrayList<>();
        for (Card card : this.cards) {
            cardInfo.add(card.printSymbolAndNumber());
        }
        return cardInfo;
    }

    public String printCardValue() {
        return String.join(", ", getCardValue());
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    boolean validateOver(int number) {
        return getScore() < number;
    }

    boolean validateUnder(int number) {
        return getScore() < number;
    }

    boolean isBlackjack() {
        if (hasAce() && getScore() == 11) {
            System.out.println("블랙잭입니다!");
            return true;
        }
        return false;
    }

    private boolean hasAce() {
        List<Boolean> includingAce = new List<Boolean>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Boolean> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Boolean aBoolean) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Boolean> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Boolean> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Boolean get(int index) {
                return null;
            }

            @Override
            public Boolean set(int index, Boolean element) {
                return null;
            }

            @Override
            public void add(int index, Boolean element) {

            }

            @Override
            public Boolean remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Boolean> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Boolean> listIterator(int index) {
                return null;
            }

            @Override
            public List<Boolean> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        for (Card card : cards) {
            includingAce.add(checkHasAce(card));
        }

        if (includingAce.contains(true)) {
            return true;
        }
        return false;
    }

    private boolean checkHasAce(Card card) {
        return card.getSymbol().equals("ACE");
    }

}
