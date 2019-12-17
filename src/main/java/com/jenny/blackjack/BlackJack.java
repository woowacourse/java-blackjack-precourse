package com.jenny.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

    private List<String> names = new ArrayList<>();

    public void askUserName(){
        Scanner sc = new Scanner(System.in);
        String input;

        while(true){
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            input = sc.nextLine();
            if(isValidNames(input)){
                break;
            }else{
                System.out.println("공백이나 중복된 이름은 사용하실 수 없습니다.");
            }
        }
    }

    public boolean isValidNames(String input){
        String[] splitedInput = input.split(",");
        int cnt = splitedInput.length;

        for(int compared = 0; compared < cnt; compared++){
            for(int each = compared+1; each < cnt; each++){
                if(splitedInput[compared].equals(splitedInput[each])
                    || splitedInput[compared] == null || splitedInput[each] == null){
                    return false;
                }
            }
        }
        saveUserNames(splitedInput);
        return true;
    }
}
