package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        User[] players;
        User dealer = new Dealer();
        players = makePlayer();
        for(User sex : players){
            System.out.println(sex);
        }
    }
    public static Player[] makePlayer(){
        Scanner s = new Scanner(System.in);
        String userInput;
        String playerNames[];
        System.out.println("게임에 참여할 사람의 이름을 입력하세요." +
                "(쉼표 기준으로 분리)");
        userInput = s.nextLine();
        if(!checkUserInput(userInput)){
            return null;
        }
        playerNames = userInput.split(",");
        return batUser(playerNames);
    }

    public static boolean checkUserInput(String userInput){
        if(userInput.length() == 0){
            System.out.println("입력이 잘못됨 ㅋㅋ");
            return false;
        }
        return true;
    }
    public static Player[] batUser(String[] playerNames){
        Player[] players = new Player[playerNames.length];
        Scanner s = new Scanner(System.in);
        int batting;
        int i= 0;
        for(String player : playerNames){
            System.out.println(player+"의 배팅금액은?");
            batting = s.nextInt();
            players[i++] = new Player(player, batting);
        }
        return players;
    }
}
