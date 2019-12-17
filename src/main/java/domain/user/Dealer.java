package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private static final int ZERO = 0;
    private static final int TEN = 10;
    private static final int SEVENTEEN = 17;
    private static final int TWENTYONE = 21;
    private static final String DEALER_CARD = "dealer's cards: ";
    private static final String COMMA = ",";
    private static final String OPEN_BRACE = " (";
    private static final String CLOSE_BRACE = ")";
    private static final String TAKE_CARD = "[takes additional card] as score less than 17";
    private static final String STAND = "[Stand] as score exceeds 16";
    
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }
    
    public List<Card> getCards() {
        return cards;
    }
    
    public int score(List<Card> cards) { // 자식클래스에서도 쓰려면 카드리스트를 파라미터로 받아야함.
        int score = ZERO;
        for (Card eachCard: cards) {
            score += eachCard.getSymbol().getScore();
            if (eachCard.getSymbol() == Symbol.ACE) { // 에이스 발견시 점수 +10
                score += TEN;
            }
        } 
        return scoreCorrection(cards, score); // 에이스를 발견했는데 점수도 21점 이상이면 -10
    }
    
    /** 점수합산 후에 호출되어 에이스가 있으면서 점수도 21점 이상이면 10점을 차감하는 메서드 */
    private int scoreCorrection(List<Card> cards, int score) {
        for (Card eachCard: cards) {
            if (eachCard.getSymbol() == Symbol.ACE && score > TWENTYONE) {
                score -= TEN;
            }
        }
        return score;
    }
    
    public String toStringStatus() {
        return DEALER_CARD 
                + String.join(COMMA, cards.stream().map(Card::toString)
                                        .collect(Collectors.toList()))
                + OPEN_BRACE + score(cards) + CLOSE_BRACE;
    }
    
    public void activateTurn() {
        System.out.println(toStringStatus());
        if (score(cards) < SEVENTEEN) {
            System.out.println(TAKE_CARD);
            addCard(CardFactory.shift());
            activateTurn(); // recursive call
            return;
        }
        System.out.println(STAND);
    }
    
    public double getNetIncome(List<Player> playerList) {
        return -playerList.stream().map(player -> player.getNetIncome(this))
                            .mapToDouble(Double::doubleValue).sum();
    }
}
