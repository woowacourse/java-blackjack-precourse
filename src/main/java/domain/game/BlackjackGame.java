package domain.game;

import domain.user.*;
import domain.ui.Utill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class BlackjackGame {

    private ArrayList<GameParticipant> participants = new ArrayList<GameParticipant>();
    private Dealer dealer = new Dealer();
    private Utill utill = new Utill();

    public void startGame() {
        initGame();
        runGame();
    }

    private void initGame() {
        ArrayList<String> playerNameList = utill.inputPlayerNameList();
        ArrayList<Double> playerBattingMoneyList = utill.inputPlayerBattingList();

        for (int i = 0; i < playerNameList.size(); i++) {
            participants.add(new Player(playerNameList.get(i), playerBattingMoneyList.get(i)));
        }
        participants.add(dealer);
    }

    private void runGame() {

        for (GameParticipant p : participants) {
            dealer.giveInitCard(p);
            utill.printCardListOfGameParticipant(p);
        }

    }


}
