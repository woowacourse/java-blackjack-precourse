package domain.user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardFactory;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer {
    private static final int TWENTYONE = 21;
    private static final int TWO = 2;
    private static final double ONE_POINT_FIVE = 1.5;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final String WHOS_CARD = "'s cards: ";
    private static final String COMMA = ",";
    private static final String BRACING = " (%s)";
    private static final String STAND = "[Stand]";
    private static final String AS_BUST = " as Bust";
    private static final String AS_SCORE_21 = " as score reaches 21";
    private static final String AS_USER_CALL = " as user's call";
    private static final String INPUT_QUERY = "type 1 for [Hit], type 2 for [Stand]:";
    private static final String INVALID_INPUT = "[Invalid Input] type (1) or (2)";
    
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

    public String getName() {
        return name;
    }
    
    public double getBettingMoney() {
        return bettingMoney;
    }
    
    /** 이름, 보유한 카드, 점수를 문자열로 반환한다.*/
    @Override
    public String toStringStatus() {
        return name + WHOS_CARD
            + String.join(COMMA, cards.stream().map(Card::toString)
                    .collect(Collectors.toList()))
            + String.format(BRACING, score(cards));
    }
    
    /** 조건을 만족하면 턴을 종료하고, 아니면 카드를 더 받을지 묻는 메서드 호출한다. */
    @Override
    public void activateTurn() {
    System.out.println(toStringStatus());
    if (score(cards) > TWENTYONE) {System.out.println(STAND + AS_BUST);}
    if (score(cards) == TWENTYONE) {System.out.println(STAND + AS_SCORE_21);}
    if (score(cards) < TWENTYONE) {
        queryHitStand();
        return;
    }
}
    
    /** 사용자 입력을 받아서 카드를 추가하고 턴 진행 메서드를 호출하거나, 턴을 종료한다. */
    private void queryHitStand() {
        try {
            System.out.println(INPUT_QUERY);
            setUserInput();
        } catch (InputMismatchException ime) {
            System.out.println(INVALID_INPUT);
            queryHitStand(); // recursive call
        }
    }
    
    private void setUserInput() {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if (input != ONE && input != TWO) {throw new InputMismatchException();}
        if (input == ONE) {
            addCard(CardFactory.shift());
            activateTurn();
        }
        if (input == TWO) {System.out.println(STAND + AS_USER_CALL);}
    }
    
    /** 플레이어와 딜러의 점수를 비교하여 블랙잭/ 승/ 무/ 패에 따른 수익을 반환한다. */
    public double getNetIncome(Dealer d) {
        if (score(cards) > TWENTYONE) {return -bettingMoney;}                                   // player bust: lose
        if (score(cards) == TWENTYONE && cards.size() == TWO) {return ifDealerBlackjack(d);} // when player blackjack
        if (d.score(d.getCards()) > TWENTYONE) {return bettingMoney;}                       // dealer bust: player win
        if (d.score(d.getCards()) == TWENTYONE && d.getCards().size() == TWO) {return -bettingMoney;} // dealer blackjack: lose
        if (score(cards) == d.score(d.getCards())) {return ZERO;}           // draw
        if (score(cards) > d.score(d.getCards())) {return bettingMoney;}    // win
        if (score(cards) < d.score(d.getCards())) {return -bettingMoney;}   // lose
        return ZERO;
    }

    private double ifDealerBlackjack(Dealer d) {
        if (d.score(d.getCards()) == TWENTYONE && d.getCards().size() == TWO) { // both blackjack: draw
            return ZERO;
        }
        return ONE_POINT_FIVE * bettingMoney; // blackjack-win
    }
}
