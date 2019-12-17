package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static final int NUM_OF_SYMBOL = 13;
    public static final int NUM_OF_TYPE = 4;
    public static final int NUM_OF_FIRST_CARD = 2;
    public static final int BLACK_JACK = 21;
    public static final int DRAW_FLAG = 16;
    private static ArrayList<Player> players = new ArrayList<>();
    private static Dealer dealer = new Dealer();
    public static void main(String[] args) {
        makePlayers();
    }

    public static void makePlayers(ArrayList<Player> players){
        String[] names = takePlayerName();
        double[] bettings = takePlayerBetting(names);
    }
    public static double[] takePlayerBetting(String[] names){
        double[] bettings
    }
    public static String[] takePlayerName(){
        Scanner s = new Scanner(System.in);
        String inputName;

        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        inputName = s.nextLine();
        checkInputLength(inputName);

        return inputName.split(",");
    }
    public static void checkInputLength(String inputName){
        String[] names = inputName.split(",");

        if(inputName.length() == 0){
            System.out.println("사용자 이름의 길이는 최소 한글자 이상입니다.");
            System.exit(1);
        }
    }
    public static void dealCardToUser(Player[] players, Dealer dealer) {
        for (Player player : players) {
            player.drawCard(NUM_OF_FIRST_CARD);
        }
        dealer.drawCard(NUM_OF_FIRST_CARD);
    }





}
