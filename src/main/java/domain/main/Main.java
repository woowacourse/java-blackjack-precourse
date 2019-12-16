package domain.main;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> playerList = new ArrayList<Player>();

        GameUser.inputTotalUsers();
        GameUser.makeUserBettingMoneyList();
        GameUser.printUserList();

        for (int i = 0; i < GameUser.userList.size(); i++){
            playerList.add(new Player(GameUser.userList.get(i),GameUser.userBettingMoneyList.get(i)));
        }

        for (Player player : playerList) {
            player.printPlayer();
        }
    }
}
