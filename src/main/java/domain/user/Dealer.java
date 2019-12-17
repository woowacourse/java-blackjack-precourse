package domain.user;

import domain.card.Card;
import domain.game.Blackjack;
import domain.game.Judgement;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer implements Participant {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void openOneCard() {
        System.out.print("\n딜러: " + cards.get(0));
    }

    @Override
    public boolean withInitCards() {
        return cards.size() == Judgement.CONDITION_INIT_CARDS;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        if (cards.size() == Judgement.CONDITION_INIT_CARDS) {
            openOneCard();
        }
    }

    @Override
    public void showCards() {
        StringBuilder msg = new StringBuilder();
        msg.append("\n딜러 카드: ");
        for (Card card : cards) {
            msg.append(card + ", ");
        }
        System.out.print(msg.substring(0, msg.length() - 2));
    }

    @Override
    public int calScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    @Override
    public void showOutcome() {
        showCards();
        System.out.print(" - 결과: " + calScore() + "점");
    }

    @Override
    public double doBalancing(double totalSettlement) {
        double settlement = totalSettlement;
        System.out.println("딜러: " + settlement);
        return settlement;
    }
}
