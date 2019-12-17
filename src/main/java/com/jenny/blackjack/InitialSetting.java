package com.jenny.blackjack;

import domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InitialSetting {

    private List<String> names = new ArrayList<>();
    private int userCnt;

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

        if(cnt == 0){
            return false;
        }
        for(int compared = 0; compared < cnt; compared++){
            for(int each = compared+1; each < cnt; each++){
                if(splitedInput[compared].equals(splitedInput[each])
                    || splitedInput[compared].equals("") || splitedInput[each].equals("")){
                    return false;
                }
            }
        }
        userCnt = cnt;
        saveUserNames(splitedInput);
        return true;
    }

    public void saveUserNames(String[] splitedInput){
        for(int idx = 0; idx < userCnt; idx++){
            names.add(splitedInput[idx]);
        }
    }

    public void askBetMoney(List<Player> players){
        Scanner sc = new Scanner(System.in);
        List<Integer> moneys = new ArrayList<>();

        for(int idx = 0; idx < userCnt; idx++){
            String betMoney;
            while(true){
                System.out.println(names.get(idx) + "의 배팅 금액은?");
                betMoney = sc.nextLine();
                if(isValidMoney(betMoney)){
                    moneys.add(Integer.parseInt(betMoney));
                    break;
                }else{
                    System.out.println("숫자만 입력하실 수 있습니다.");
                }
            }
            registerUser(idx, Integer.parseInt(betMoney), players);
        }
    }

    public boolean isValidMoney(String betMoney){
        int len = betMoney.length();
        for(int idx = 0; idx < len; idx++){
            char digit = betMoney.charAt(idx);
            if(digit < '0' || digit > '9'){
                return false;
            }
        }
        return true;
    }

    public void registerUser(int idx, int betMoney, List<Player> players){
        players.add(new Player(names.get(idx), betMoney));
    }
}
