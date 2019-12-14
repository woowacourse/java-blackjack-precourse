package domain.manager;

import domain.user.*;
import domain.card.*;

import java.util.*;

public class GameManager {

    private static final String delimiter = ",";
    private static final Scanner SC = new Scanner(System.in);

    private List<Player> players;

    public static void main(String[] args) {
        GameManager game = new GameManager();
        game.startGame();
        game.playGame();
    }

    public void startGame() {
        List<String> playerNames = Arrays.asList(setPlayerNames());//player 이름 입력
        List<Integer> bettingMoney = setBettingMoney(playerNames);//각 player의 배팅 금액 입력
        //딜러, 플레이어 각각 두장의 카드 발급
        //현재 상황 출력
    }

    public String[] setPlayerNames(){
        Scanner sc = SC;
        System.out.println("게임에 참여할 사람들의 이름을 입력하세요. (쉼표 기준으로 분리함)");
        String input = sc.nextLine();
        String[] playerNamesArray = input.split(delimiter);
        return playerNamesArray;
    }

    public List<Integer> setBettingMoney(List<String> playerNames){
        Scanner sc = SC;
        List<Integer> bettingMoney = new List<Integer>;
        for(String tmp: playerNames){
            System.out.println(tmp + "의 배팅 금액은?");
            Integer input = sc.nextInt();
            bettingMoney.add(input);
        }
        return bettingMoney;
    }


    public void playGame() {
        //각 player가 카드를 더 받을지
        //딜러가 카드를 더 받을
    }

}
