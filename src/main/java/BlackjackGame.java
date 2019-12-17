/*
 * class: BlackjackGame
 *
 * version: 1.0v
 *
 * date: 2019.12.17
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.17
 */

import domain.construtor.CardDrawing;
import domain.construtor.ConstructionPlayerAndDealer;
import domain.winner.SelectionWinner;
import domain.output.View;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

/**
 * 게임시작
 */
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