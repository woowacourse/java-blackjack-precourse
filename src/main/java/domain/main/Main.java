package domain.main;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

public class Main {
    public static void main(String[] args) {

        GameUser.inputTotalUsers();
        GameUser.makeUserBettingMoneyList();
        GameUser.makePlayerList();

        System.out.println("딜러와 " + String.join(", ", GameUser.userList) + "에게 2장씩 나누었습니다.");

        for (Player player : GameUser.playerList) {
            player.getStartCard();
            player.printPlayerBettingMoney();
            player.printHaveCardList();
            player.cardDrawOrPass();
            player.checkAceInCardList();
            player.chooseCalculateMethod();
            System.out.println(player.printPlayerName() + "님의 결과 :" + player.result());
        }


        Dealer dealer = new Dealer("딜러", 0);
        dealer.getStartCard();
        dealer.printPlayerBettingMoney();
        dealer.cardDrawOrPass();
        dealer.checkAceInCardList();
        dealer.printHaveCardList();
        dealer.result();

    }
}
