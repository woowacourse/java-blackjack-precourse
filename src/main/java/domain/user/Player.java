package domain.user;

import domain.card.Card;
import domain.game.BlackJack;
import domain.game.Message;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Player는 게임 참여자를 의미하는 객체이다.
 * 카드를 더하고 배팅을 하는 기능을 가지고 있다.
 *
 * 로직상 별도의 money 변수가 있다면, 배팅에 따른 이익/손실을 계산하기에 용이할 것으로 보인다.
 * 그러나 요구사항 상 인스턴스 필드를 추가할 수 없으므로,
 * 또한 베팅머니라는 변수의 값 변경이 불가(final) 하므로
 * 실제 구현은 승패 여부에 따라 결과를 반환하는 함수의 추가 정도로 마친다.
 *
 * @author kafka
 * @version 1.2
 */
public class Player extends Dealer{
    /**
     * name은 플레이어의 이름을 저장하는 String형 인스턴스 변수이다.
     */
    private final String name;
    /**
     * bettingMoney는 플레이어가 배팅한 금액을 저장하는 int형 인스턴스 변수이다.
     */
    private final double bettingMoney;

    /**
     * 생성자 메서드는 이름과 베팅머니 값을 설정한다.
     *
     * @param name 이름을 전달하는 변수이다.
     * @param bettingMoney 배팅 금액을 전달하는 변수이다.
     */
    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    /**
     * getResultMoney는 딜러의 점수를 바탕으로 플레이어가 얻을 돈을 계산하는 메서드이다.
     * 첫 패에 바로 블랙잭이 나올 경우는 0.5배를 추가 수익으로 얻고,
     * 승리할 경우에는 배팅한 금액만큼의 수익을,
     * 패배할 경우에는 배팅한 금액만큼의 손실을 얻는다.
     * 무승부시에는 원금을 돌려받으므로 수익이 없다.
     *
     * @param dealerScore 딜러가 얻은 점수를 전달하는 int형 변수이다.
     * @return 딜러의 점수와 플레이어 점수를 바탕으로 플레이어가 얻을 돈을 반환한다. 잃을 경우 음수로 반환된다.
     */
    public double getResultMoney(int dealerScore) {
        if(isBlackJackInFirstHand()){
            return bettingMoney / 2.0;
        }
        if(dealerScore > BlackJack.MAX_SCORE || (getScore() <= BlackJack.MAX_SCORE && dealerScore < getScore())){
            return bettingMoney;
        }
        if(getScore() > BlackJack.MAX_SCORE || getScore() < dealerScore){
            return bettingMoney * -1.0;
        }
        return 0.0;
    }

    /**
     * getResultMoneyString은, 플레이어가 최종적으로 얻을 돈을 이름과 함꼐 문자열로 전달해주는 메서드이다.
     *
     * @param dealerScore 딜러가 얻은 점수를 전달하는 int형 변수이다.
     * @return [이름 : n] 형태의 문자열로 반환된다. (* n:수익)
     */
    public String getResultMoneyString(int dealerScore){
        return name+": "+Integer.toString((int)getResultMoney(dealerScore));
    }

    /**
     * isBlackJackInFirstHand는 처음 뽑은 두 패가 블랙잭(점수합 21)인 경우를 판단한다.
     *
     * @return 첫 두 패의 점수 합이 블랙잭일 경우 true를, 아닐 경우 false를 반환한다.
     */
    private boolean isBlackJackInFirstHand() {
        return getCardListSize() == 2 && getScore() == BlackJack.MAX_SCORE;
    }

    /**
     * getCardStringWithName은 Dealer 클래스에서 오버라이딩된 메서드이다.
     * 카드의 목록에 플레이어의 이름까지 붙여 문자열 형태로 반환한다.
     *
     * @return 유저의 이름과 카드 목록이 합쳐진 문자열을 반환한다.
     */
    @Override
    public String getCardStringWithName() {
        return name + " " + getCardString();
    }

    /**
     * checkDrawMore는 Dealer 객체의 메서드에 대한 오버라이드 메서드이다.
     * 그러나, 딜러와 달리 플레이어는 카드를 더 뽑을 수 없는 경우
     * (블랙잭 내지 그 이상의 점수가 나오는 경우)를 제외하면
     * 질문을 통해 뽑도록 하여야 한다.
     *
     * @return 카드를 더 뽑을 수 있는지 여부를 boolean으로 반환한다.
     */
    @Override
    public boolean checkDrawMore(){
        if(getScore() >= BlackJack.MAX_SCORE){
            return false;
        }
        return checkDrawMoreByInput();
    }

    /**
     * checkDrawMoreByInput은 터미널 인풋을 통해 패를 더 뽑을지 물어보는 메서드이다.
     * Dealer 클래스에서 오버라이딩된 메서드이다.
     * 만약 카드를 더 뽑을 수 없는 경우(이미 블랙잭이거나 게임 오버된 경우)는 예외로 처리한다.
     *
     * @return 카드를 더 뽑을 지 여부를 반환한다.
     */
    private boolean checkDrawMoreByInput(){
        Scanner sc = new Scanner(System.in);
        String inputString;
        System.out.print(name + Message.DRAW_PLAYER_QUESTION);
        try {
            inputString = sc.nextLine();
            if (!inputString.equals("y") && !inputString.equals("n")) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.print(Message.ERROR_INPUT);
            return checkDrawMoreByInput();
        }
        return inputString.equals("y");
    }
}
