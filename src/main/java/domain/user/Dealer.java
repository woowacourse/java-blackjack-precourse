package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    /*
     * Dealer 클래스 입니다.
     */
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public int getScoreInternal() { // 내부에서만 사용하는 점수반환 메소드 입니다.
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    public int getScore() { // 다른클래스에서 사용하기위한 점수반환 메소드 입니다.
        int score = 0;
        for (Card card : cards) {
            score += verifyAce(card);
        }
        return score;
    }

    public List<String> getDeck() { // 현재 가지고 있는 카드를 반환합니다.
        List<String> deck = new ArrayList<String>();
        for (Card card : cards) {
            String cardInfo = verifyAce(card) + card.getType();
            deck.add(cardInfo);
        }
        return deck;
    }

    public int verifyAce(Card card) { // 가지고있는 카드중 에이스임을 판별합니다.
        if(card.getScore() == 1) {
            return verifyAceScore();
        } else {
            return card.getScore();
        }
    }

    public int verifyAceScore() { // 에이스의 점수를 판별해줍니다.
        int acePoint = 10 * verifyAceCount();
        if (getScoreInternal() + acePoint < 21) {
            return 11;
        }
        return 1;
    }

    public int verifyAceCount() { // 가지고있는 에이스의 숫자를 파악합니다.
        int countAce = 0;
        for (Card card : cards) {
            countAce += verifyAceCountDetail(card);
        }
        return countAce;
    }

    public int verifyAceCountDetail(Card card) {
        if(card.getScore() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

}
