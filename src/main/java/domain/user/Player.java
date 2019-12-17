package domain.user;

import domain.card.Card;
import domain.manager.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
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

    // TODO 추가 기능 구현

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public List<Card> getCard() {
        return cards;
    }

    public int getScore() {
        int score = 0;

        for (Card card : cards) {
            score += card.getSymbol().getScore();
        }

        if (score > Constant.BUST_POINT)
            return hasACE(score, 0);
        return score;
    }

    public int hasACE(int score, int cnt) {
        List<Card> ace = cards.stream().filter(c -> c.isACE()).collect(Collectors.toList());
        for (int i = 0; i < cnt; i++) {
            ace.remove(0);
        }

        if (ace.size() == 1) return score - 10;
        if (ace.size() > 0 && score > Constant.BUST_POINT) return hasACE(score, cnt - 1);
        return score;
    }

    public void showCards() {
        System.out.println(name + " : " + cards);
    }

    public void showMoney(double result) {
        if (result == Constant.WIN && cards.size() == 2)
            result = Constant.WIN_BLACKJACK;
        System.out.println(name + " : " + (int)(bettingMoney * result));
    }

    public void showResult() {
        System.out.println(name + " : " + cards + " - 결과 : " + getScore());
    }
}
