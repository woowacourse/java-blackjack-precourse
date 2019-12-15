package domain;

import java.util.Scanner;

public class ConsoleMain {
    private static Scanner sc = new Scanner(System.in);
    private static String[] playerName;

    public static void main(String[] args){
        inputName();
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
    }
}
