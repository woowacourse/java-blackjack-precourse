package domain.user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 전체 게임 참여자를 생성하고, 리스트로 담는 객체
 */
public class EntirePlayer {
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final String SET_BET_MONEY = "Please Set %s's Betting Money%n";
    private static final String TYPE_NAME = "Please Type Name of Players (seperate with comma\",\")";
    private static final String INVALID_INPUT = "[Invalid Input] type natral number";
    private static final String COMMA = ",";
    
    private List<Player> playerList = new ArrayList<Player>();
    
    /** 객체를 생성하면서 플레이어 이름과 배팅액을 입력받는다. */
    public EntirePlayer() {
        for (String eachName: setPlayerNames()) {
            System.out.printf(SET_BET_MONEY, eachName);
            playerList.add(new Player(eachName, setBettingMoney()));
        }
    }
    
    public List<Player> getList() {
        return this.playerList;
    }
    
    private List<String> setPlayerNames() {
        List<String> nameList = new ArrayList<String>();
        System.out.println(TYPE_NAME);
        Scanner input = new Scanner(System.in);
        for (String eachName : input.next().split(COMMA)) {
            nameList.add(eachName);
         }
        return nameList;
    }
    
    private int setBettingMoney() {
        int bettingMoney = ZERO;
        try {bettingMoney = innerSetBettingMoney(bettingMoney);}
        catch (InputMismatchException ime) {
            System.out.println(INVALID_INPUT);
            bettingMoney = setBettingMoney();  //오류시 재귀
        }
        return bettingMoney;
    }
    
    private int innerSetBettingMoney(int bettingMoney) {
        Scanner sc = new Scanner(System.in);
        bettingMoney = sc.nextInt();
        if (bettingMoney < ONE) {
            throw new InputMismatchException();
         }
        return bettingMoney;
    }
}
