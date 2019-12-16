/*
 * test
 * */


import domain.construtor.ConstructPlayerAndDealer;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {
    static List<Player> playerList;
    static ConstructPlayerAndDealer construct  = new ConstructPlayerAndDealer();

    public static void main(String[] args) {
        playerList = construct.constructor();
    }

}