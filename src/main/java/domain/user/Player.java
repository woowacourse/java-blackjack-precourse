package domain.user;

import domain.card.Card;
import domain.main.GamePlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    public final int NUMBER_OF_START_CARDS = 2;
    public final String ANSWER_YES = "y";
    public final String ANSWER_NO = "n";
    public static final int STANDARD_POINT = 21;
    public final int ACE_POINT = 10;
    public final int FAIL_RATE = -1;
    public final double SUCCESS_RATE = 1.5;
    public static int playerScore = 0;
    public List<Boolean> aceList = new ArrayList<Boolean>();
    public String playerChoice;
    public int NumberOfAce = 0;


    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public String printPlayerName(){
        return name;
    }
    public void printPlayerBettingMoney() {
        System.out.println(name + "의 배팅금액 : " + bettingMoney);
    }

    public void getStartCard() {
        for (int i = 0; i < NUMBER_OF_START_CARDS; i++) {
            cards.add(GamePlay.addNewCard());
        }
    }

    public void printHaveCardList() {
        System.out.print(name + "님의 카드 : ");
        for (Card card : cards) {
            System.out.print(card.cardToString() + "\t");
        }
        System.out.println();
    }

    public boolean checkInputPlayerChoice(String check) {
        return check.equals(ANSWER_YES) || check.equals(ANSWER_NO);
    }

    public String inputPlayerChoice() {
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("카드를 뽑으시려면 y 넘기려면 n 을 입력하세요.");
            playerChoice = s.nextLine();
        } while (!checkInputPlayerChoice(playerChoice));
        return playerChoice;
    }

    public boolean checkPlayerChoice() {
        return inputPlayerChoice().equals(ANSWER_YES);
    }

    public void cardDrawOrPass() {
        while (checkPlayerChoice() && playerScore <= STANDARD_POINT) {
            addCard(GamePlay.addNewCard());
            printHaveCardList();
            chooseCalculateMethod();
        }
        System.out.println("더이상 뽑을 수 없습니다.");
    }

    public void checkAceCard(Card card) {
        aceList.add(card.getSymbol().getExpression().equals("A"));
    }

    public void getNumberOfAceCard(Card card) {
        if (card.getSymbol().getExpression().equals("A")) {
            NumberOfAce++;
        }
    }

    public void checkAceInCardList() {
        for (Card card : cards) {
            checkAceCard(card);
            getNumberOfAceCard(card);
        }
    }

    public void chooseCalculateMethod() {
        playerScore = 0;
        if (aceList.contains(true)) {
            playerScore = calculateScoreWithAce();
        }
        playerScore = calculateScoreWithoutAce();
    }

    public int calculateScoreWithoutAce() {
        for (Card card : cards) {
            playerScore += card.getSymbol().getScore();
        }
        return playerScore;
    }

    public int calculateScoreWithAce() {
        playerScore = calculateScoreWithoutAce();
        for (int i = 0; i < NumberOfAce; i++) {
            playerScore = selectElevenOrOne(playerScore);
        }
        return playerScore;
    }

    public int selectElevenOrOne(int playerScore) {
        if ((Math.abs(STANDARD_POINT - (playerScore+ACE_POINT)) < Math.abs(STANDARD_POINT - playerScore)) &&
            (playerScore+ACE_POINT <= STANDARD_POINT)){
            return playerScore + ACE_POINT;
        }
        return playerScore;
    }

    public boolean isPlayerFail(){
        return playerScore > STANDARD_POINT;
    }

    public boolean isPlayerSuccess(){
        return playerScore == STANDARD_POINT && cards.size()==NUMBER_OF_START_CARDS;
    }
    public double result(){
        if (isPlayerFail()){
            return bettingMoney * FAIL_RATE;
        }
        if (isPlayerSuccess()){
            return bettingMoney * SUCCESS_RATE;
        }
        return bettingMoney;
    }

}
