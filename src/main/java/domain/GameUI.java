package domain;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GameUI {

    Scanner sc = new Scanner(System.in);
    String[] players;
    List<Integer> bettingMoney = new ArrayList<Integer>();

    public void play() {
        players = inputPlayer();

        for (String p : players) {
            bettingMoney.add(inputBetting(p));
        }

        GameBoard board = new GameBoard(players, bettingMoney);

    }



    public String[] inputPlayer() {
        String INPUTPLAYER = "게임에 참여할 사람의 이름을 입력하세요 (쉼표로 분리)";
        String[] player;
        String input;

        do {
            System.out.println(INPUTPLAYER);
            input = sc.nextLine();
            players = input.split(",");

        } while (!(players.length >= 2));        //두 명 이상 입력이 안된 경우

        for (String p : players)
        System.out.println(p);
        return players;
    }

    public int inputBetting(String name) {
        int betting = 0;
        boolean success = false;

        System.out.println(name+"의 베팅금액은?");
        do {
            try {
                betting = sc.nextInt();
                if (betting <= 0) {
                    throw new Exception();
                }
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력하세요!");
                sc = new Scanner(System.in);

            } catch (Exception e) {
                System.out.println("더 큰 수를 입력하세요");
                sc = new Scanner(System.in);
            }
        } while (!success);

        return betting;
    }




}
