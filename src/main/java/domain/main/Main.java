package domain.main;

import domain.card.Card;
import domain.user.Player;

public class Main {
    public static void main(String[] args) {

        GameUser.inputTotalUsers();
        GameUser.makeUserBettingMoneyList();
        GameUser.makePlayerList();

        for (Player player : GameUser.playerList){
            player.getStartCard();
            player.haveCardList();
        }


    }
}
