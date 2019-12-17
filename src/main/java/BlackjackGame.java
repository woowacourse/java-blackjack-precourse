/*
 * test
 * */


import domain.construtor.CardDrawing;
import domain.construtor.ConstructPlayerAndDealer;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {
    static List<Player> playerList;
    static Dealer dealer;
    static ConstructPlayerAndDealer construct  = new ConstructPlayerAndDealer();
    static CardDrawing drawing = new CardDrawing();

    public static void main(String[] args) {
        playerList = construct.constructPlayer();
        dealer = construct.constructDealer();
        drawing.startShuffle(playerList,dealer);
    }

}