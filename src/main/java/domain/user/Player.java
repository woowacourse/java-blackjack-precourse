package domain.user;

import domain.card.Card;
import utils.ConsoleOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    protected static final int SCORE_LIMIT = 21;

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void printCards() {
        ConsoleOutput.printCards(name, cards);
    }

    private int calculateScore() {
        return (cards.stream()
                .mapToInt(x -> x.getScore())
                .sum());
    }

    public int getScoreTest() {
        return calculateScore();
    }

    public void drawNewCard() {
        try {
            System.out.println(name+"님, 한 장의 카드를 더 받겠습니까? (예는 Y, 아니오는 N)");
            UserInput.inputYesOrNo();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            drawNewCard();
            printCards();
        }
    }

    public boolean isBlackJack() {
        return (cards.size() == 2 && (calculateScore() == 21 || calculateScore() == 11 && isAceExists()));
    }

    private boolean isAceExists() {
        return (cards.stream().filter(x -> x.isAce()).count() == 1);
    }

    public boolean isBusted() {
        return (calculateScore() > SCORE_LIMIT);
    }
    // 임시
    public String getNameTest() {
        return this.name;
    }
}
