package domain.game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardGame {

    // 후에 rafactoring
    static Scanner sc = new Scanner(System.in);

    public static void insertPlayerNameStr(){

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

        System.out.println(playerNameList.toString());

    }

    public static String[] dividePlayerNameStrToRest(String playerStr){
        String[] playerNameArr = playerStr.split(",");
        return playerNameArr;
    }

    public static List<String> deleteSpacePlayerNameArr(String[] playerNameArr){
        List<String> playerNameList = Arrays.asList(playerNameArr).stream()
                .map(element -> element.replace(" ", ""))
                .collect(Collectors.toList());

        return playerNameList;
    }

    public static boolean checkPlayerNameList(List<String> playerNameList){
        return playerNameList.stream().allMatch(element -> !element.equals(""));
    }

    public static void run(){
        insertPlayerNameStr();
    }

}
