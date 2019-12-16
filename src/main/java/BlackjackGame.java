/*
 * test
 * */


import domain.construtor.ConstructPlayerAndDealer;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {
    static List<Player> playerList;
    static Dealer dealer;
    static ConstructPlayerAndDealer construct  = new ConstructPlayerAndDealer();

    public static void main(String[] args) {
        playerList = construct.constructorPlayer();
        dealer = construct.constructorDealer();

    }

}