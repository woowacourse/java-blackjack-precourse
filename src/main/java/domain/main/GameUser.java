package domain.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameUser {
    public static String totalUsers;
    public static List<String> userList = new ArrayList<String>();
    public static List<Double> userBettingMoneyList = new ArrayList<Double>();
    public static Double bettingMoney;

    public static void inputTotalUsers() {
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("게임에 참여하는 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            totalUsers = s.nextLine();
            makeUserList();
        }while(checkUserList());
    }
    public static void makeUserList() {
        for (String user : totalUsers.split(",")) {
            userList.add(user);
        }
    }
    public static boolean checkUserList(){
        return userList.contains("")||userList.isEmpty();
    }

    public static void InputBettingMoney() {
        Scanner s = new Scanner(System.in);
        while(!s.hasNextDouble()){
            s.next();
            System.out.println("잘못된 입력입니다. 배팅 금액을 다시 입력해주세요.");
        }
        userBettingMoneyList.add(s.nextDouble());
    }
    public static void makeUserBettingMoneyList() {
        for (String user : userList){
            System.out.printf("%s님의 배팅 금액은?\n",user);
            InputBettingMoney();
        }
    }
    public static void printUserList() {
        for (String user : userList){
            System.out.println(user);
        }
        for (double bettingMoney : userBettingMoneyList){
            System.out.println(bettingMoney);
        }
    }
}
