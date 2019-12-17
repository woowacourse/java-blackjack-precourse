package domain.game;

import domain.user.*;
import domain.ui.Utill;

import java.util.ArrayList;

public class BlackjackGame {

    private ArrayList<GameParticipant> participants = new ArrayList<GameParticipant>();
    private Dealer dealer = new Dealer();
    private Utill utill = new Utill();

    public void startGame() {
        initGame();
        runGame();
        finishGame();
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
        giveInitCardToAllParticipants();
        if (isThereBlackjack()) {
            return;
        }

    }

    private void finishGame(){
        calculationFinalRevence();
    }

    private void giveInitCardToAllParticipants() {
        for (GameParticipant p : participants) {
            dealer.giveInitCard(p);
            utill.printCardListOfGameParticipant(p);
        }
    }

    private boolean isThereBlackjack() {
        boolean blackjack = false;
        for (GameParticipant p : participants) {
            blackjack = (blackjack || p.isBlackjack());
        }
        return blackjack;
    }

    private void calculationFinalRevence(){

    }


}
