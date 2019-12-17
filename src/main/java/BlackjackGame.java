/*
 * test
 * */


import domain.construtor.CardDrawing;
import domain.construtor.ConstructionPlayerAndDealer;
import domain.winner.SelectionWinner;
import domain.output.View;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {
    static final int BLACKJACK = 21;
    static SelectionWinner winner = new SelectionWinner();
    static List<Player> playerList;
    static Dealer dealer;
    static ConstructionPlayerAndDealer construct  = new ConstructionPlayerAndDealer();
    static CardDrawing drawing = new CardDrawing();
    static View view = new View();

    public static void main(String[] args) {
        playerList = construct.constructPlayer();
        dealer = construct.constructDealer();
        drawing.startShuffle(playerList,dealer);
        if(playerList.stream().anyMatch(player -> player.calculateSymbol() == BLACKJACK)){
            view.resultCardMsg(playerList,dealer);
            winner.startBlackjack(playerList,dealer);
            return;
        }
        drawing.drawAllPlayer(playerList,dealer);
        drawing.drawDealerCard(dealer.calculateSymbol(),dealer);
        winner.winner(playerList,dealer);
    }

}