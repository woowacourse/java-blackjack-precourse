package domain;

import java.util.Scanner;

public class ConsoleMain {
    private static Scanner sc = new Scanner(System.in);
    private static String[] playerName;
    private static int[] betting;
    private static int playerNumber;
    private static final int ZERO_BETTING = 0;

    public static void main(String[] args){
        inputName();
        inputBetting();
    }

    private static void inputName(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요."+
                            "(쉼표 기준으로 분리)");

        String input = sc.nextLine().trim();
        if(input.isEmpty()){
            inputName();
            return;
        }
        playerName = input.split(",");
        playerNumber = playerName.length;
    }

    private static void inputBetting(){
        betting = new int[playerNumber];
        for(int i = 0; i < playerName.length; i++){
            while(betting[i] <= ZERO_BETTING){
                System.out.println(playerName[i] + "의 배팅 금액은? (금액은 0원 이상이어야 합니다.)");
                betting[i] = sc.nextInt();
            }
        }
    }
}
