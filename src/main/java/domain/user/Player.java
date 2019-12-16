package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private static final int BLACKJACK = 21;
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public double reward = 0;
    public boolean burstPlayer = false;

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
        int aceCount = 0;
        for (int i = 0; i < cards.size(); i++) {
            score += cards.get(i).getScore();
            aceCount += getScoreFromCard(score, i);
        }
        if (aceCount > 0) {
            checkAceScore(score);
        }
        return score;
    }

    public int getScoreFromCard(int score, int i) {
        int aceCount = 0;
        if (cards.get(i).ifCardisAce()){
            aceCount = 1;
        }
        return aceCount;
    }

    public int checkAceScore(int score) {
        if (score + 10 <= BLACKJACK) {
            score += 10;
        }
        return score;
    }

    public void getBettingMoney(double interest) {
        reward = bettingMoney * interest;
    }

    public double getReward() {
        return reward;
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

    public boolean ifBlackJack() {
        if((cards.get(0).getScore() + cards.get(1).getScore()) == BLACKJACK) {
            return true;
        }
        return false;
    }
}
