/*
 * test
 * */


import domain.construtor.CardDrawing;
import domain.construtor.ConstructionPlayerAndDealer;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {
    static List<Player> playerList;
    static Dealer dealer;
    static ConstructionPlayerAndDealer construct  = new ConstructionPlayerAndDealer();
    static CardDrawing drawing = new CardDrawing();

    public static void main(String[] args) {
        playerList = construct.constructPlayer();
        dealer = construct.constructDealer();
        drawing.startShuffle(playerList,dealer);
        drawing.drawAllPlayer(playerList);
    }

}