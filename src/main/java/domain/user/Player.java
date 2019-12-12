package domain.user;

import domain.card.Card;

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

    public String getCardString() {
        return (name+" 카드: "+concatCardList());
    }

    public String getFinalCardString() {
        return (name+" 카드: " + concatCardList() + " - 결과: "+this.calculateScore()+printBust());
    }

    private String concatCardList() {
        return String.join(", ", getKoreanName());
    }

    private List<String> getKoreanName() {
        List<String> names = new ArrayList<>();
        cards.stream().forEach(x -> names.add(x.toKorean()));
        return names;
    }

    private int calculateScore() {
        int score = (cards.stream()
                .mapToInt(x -> x.getScore())
                .sum());
        if(cards.stream().filter(x -> x.getScore() == 1).count() != 0
            && score <= 11) {
            score += 10;
        }
        return score;
    }

    private String printBust() {
        if(this.isBusted()) {
            return (" (버스트)");
        }
        return "";
    }

    public int getScoreTest() {
        return calculateScore();
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

    public String isHit() {
        return (name+"은(는) 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
    }

    // 스탠드오프. 무승부.
    public String getStandOff() {
        return (name+": "+0);
    }

    // 블랙잭이 나와 우승.
    public String winBlackJack() {
        return (name+": "+1.5*bettingMoney);
    }

    // 가장 21에 가까운 수로 우승.
    public String winNearestScore() {
        return (name+": "+bettingMoney);
    }

    // 패배
    public String loseAll() {
        return (name+": "+-1*bettingMoney);
    }

    // 임시
    public String getNameTest() {
        return this.name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
