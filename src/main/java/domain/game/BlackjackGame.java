package domain.game;

import domain.user.*;
import domain.ui.Utill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class BlackjackGame {

    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer = new Dealer();
    private Utill utill = new Utill();

    public void startGame() {
        initGame();
        runGame(players,dealer);
    }

    private void initGame() {
        ArrayList<String> playerNameList = utill.inputPlayerNameList();
        ArrayList<Double> playerBattingMoneyList = utill.inputPlayerBattingList();

        for (int i = 0; i < playerNameList.size(); i++) {
            players.add(new Player(playerNameList.get(i), playerBattingMoneyList.get(i)));
        }

    }

    private void runGame(ArrayList<Player> players, Dealer dealer) {
        ArrayList<GameParticipant> participants = new ArrayList<GameParticipant>(players);
        participants.add(dealer);

        for (GameParticipant p : participants) {
            dealer.giveInitCard(p);
        }

    }


}
