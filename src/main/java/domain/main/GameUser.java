package domain.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameUser {
    public static String totalUsers;
    public static List<String> userList = new ArrayList<String>();

    public static void inputTotalUsers() {
        Scanner s = new Scanner(System.in);
        totalUsers = s.nextLine();
    }

    public static void makeUserList() {
        for (String user : totalUsers.split(",")) {
            userList.add(user);
        }
    }

    public static void printUserList() {
        for (String user : userList){
            System.out.println(user);
        }

    }
}
