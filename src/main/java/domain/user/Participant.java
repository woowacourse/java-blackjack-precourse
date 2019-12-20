package domain.user;

import domain.card.Card;
import domain.game.Judgement;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    protected final List<Card> cards = new ArrayList<>();

    public boolean withInitCards() {
        return cards.size() == Judgement.CONDITION_INIT_CARDS;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void showCards(String name) {
        StringBuilder msg = new StringBuilder();
        msg.append("\n" + name + " 카드: ");
        for (Card card : cards) {
            msg.append(card + ", ");
        }
        System.out.print(msg.substring(0, msg.length() - 2));
    }

    public int calScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    public void showOutcome(String name) {
        showCards(name);
        System.out.print(" - 결과: " + calScore() + "점");
    }

    abstract double doBalancing(double amount);
}
