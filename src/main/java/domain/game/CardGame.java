package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardGame {

    // 후에 rafactoring
    static Scanner sc = new Scanner(System.in);
    static final int FIRST_CARD_COUNT = 2;

    public static List<String> insertPlayerNameStr(){

        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerStr = sc.nextLine();

        String[] playerNameArr = dividePlayerNameStrToRest(playerStr);
        List<String> playerNameList = deleteSpacePlayerNameArr(playerNameArr);

        while(!checkPlayerNameList(playerNameList)){
            System.out.println("이름에 공백이 있습니다! 다시 입력해주세요");
            playerStr = sc.nextLine();
            playerNameArr = dividePlayerNameStrToRest(playerStr);
            playerNameList = deleteSpacePlayerNameArr(playerNameArr);
        }

        return playerNameList;

    }

    public static String[] dividePlayerNameStrToRest(String playerStr){
        return playerStr.split(",");
    }

    public static List<String> deleteSpacePlayerNameArr(String[] playerNameArr){
        return Arrays.asList(playerNameArr).stream()
                .map(element -> element.replace(" ", ""))
                .collect(Collectors.toList());
    }

    public static boolean checkPlayerNameList(List<String> playerNameList){
        return playerNameList.stream()
                .allMatch(element -> !element.equals(""));
    }

    public static List<Player> insertPlayerMoneyStr(List<String> playerNameList){

        List<Player> playerList = new ArrayList<>();

        // 여기 bettingMoney가 음수일 경우 처리해줘야 함. 함수로 빼면 되겠다. 그냥
        playerNameList.stream().
                forEach(element -> {
                    System.out.println(element + "의 베팅 금액은?");
                    int bettingMoney = insertBettingMoney();
                    playerList.add(new Player(element, bettingMoney));
                });

        return playerList;
    }

    public static int insertBettingMoney(){
        int bettingMoney = sc.nextInt();
        while(checkBettingMoney(bettingMoney)){
            System.out.println("베팅 금액이 너무 적습니다! 다시 입력해주세요!");
            bettingMoney = sc.nextInt();
        }
        return bettingMoney;
    }

    public static boolean checkBettingMoney(int bettingMoney){
        if(bettingMoney <= 0){
            return true;
        }
        return false;
    }

    public static void printYourCards(List<Player> playerList, Dealer dealer){
        dealer.printMyCards();
        playerList.forEach(element -> element.printMyCards());
    }

    public static void run(){
        List<String> playerNameList = insertPlayerNameStr();
        System.out.println(playerNameList.toString());
        List<Player> playerList = insertPlayerMoneyStr(playerNameList);
        List<Card> cardList = CardFactory.create();
        System.out.println(playerList.toString());
        printYourCards(playerList, dealer);

    }

}
