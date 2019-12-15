package domain.game;

import java.util.Scanner;

public class CardGame {

    public static void run(){
        Scanner sc = new Scanner(System.in);

        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        //
        String joinPlayerStr = sc.nextLine();

        String[] joinPlayerNameArr = joinPlayerStr.split(",");
        int[] joinPlyaerMoneyArr = new int[joinPlayerNameArr.length];

    }

}
