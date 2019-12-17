package domain.game;

import domain.user.*;
import domain.ui.Utill;

import java.util.ArrayList;



public class BlackjackGame {

    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer = new Dealer();
    private Utill utill = new Utill();

    public void startGame(){
        initGame();

    }

    private void initGame(){
        ArrayList<String> playerNameList = utill.inputPlayerNameList();
        ArrayList<Double> playerBattingMoneyList = utill.inputPlayerBattingList();

        for ( int i = 0; i < playerNameList.size(); i++) {
            players.add(new Player(playerNameList.get(i), playerBattingMoneyList.get(i)));
        }

    }



}
