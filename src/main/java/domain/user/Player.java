package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    private double reward = 0;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public int sumScore() {
        int score = 0;
        for (int i = 0; i < cards.size(); i++) {
            score += cards.get(i).getScore();
        }
        return score;
    }

    public void getBettingMoney(double interest) {
        reward = this.bettingMoney * interest;
    }

    public String toString() {
        String str = "";
        String joinStr = String.join(",", cards.toString());
        str += joinStr.substring(1, joinStr.length()-1);
        return str;
    }

    //삭제 할 기능
    public String getinfo() {
        return name;
    }
    public double getbet(){
        return bettingMoney;
    }
    public String getCard() {

        String cardStr = String.join(", ",cards.toString());
        return cardStr;
    }
}
