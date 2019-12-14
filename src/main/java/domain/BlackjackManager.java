package domain;

import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 전반을 관리하는 객체
 */
public class BlackjackManager {
    GamePrinter gamePrinter = new GamePrinter();
    List<Player> players = new ArrayList<>();

    void makePlayer(){
        String[] playerName = gamePrinter.getPlayerNameFromUser();
        for(int i = 0; i < playerName.length; i++){
            int bettingMoney = gamePrinter.getBettingMoneyFromUser(playerName[i]);
            players.add(new Player(playerName[i], bettingMoney));
        }
    }
}
