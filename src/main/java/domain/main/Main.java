package domain.main;

import domain.card.Card;
import domain.user.Player;

public class Main {
    public static void main(String[] args) {

        GameUser.inputTotalUsers();
        GameUser.makeUserBettingMoneyList();
        GameUser.makePlayerList();

        System.out.println("딜러와 "+String.join(", ",GameUser.userList)+"에게 2장씩 나누었습니다.");

        for (Player player : GameUser.playerList){
            player.getStartCard();
            player.printPlayerBettingMoney();
            player.cardDrawOrPass();
            System.out.println(player.calculateScoreWithoutAce());
        }


    }
}
