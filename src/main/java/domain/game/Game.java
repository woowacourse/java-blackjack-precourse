package domain.game;

import domain.user.*;
import domain.ui.Utill;

import java.util.ArrayList;

public class Game {

    private ArrayList<GameParticipant> participants = new ArrayList<GameParticipant>();
    private Dealer dealer = new Dealer();
    private Utill utill = new Utill();


    public void playGame() {
        initGame();
        runGame();
        finishGame();
    }

    private void initGame() {
        ArrayList<String> playerNameList = utill.inputPlayerNameList();
        ArrayList<Double> playerBattingMoneyList = utill.inputPlayerBattingList(playerNameList);

        for (int i = 0; i < playerNameList.size(); i++) {
            participants.add(new Player(playerNameList.get(i), playerBattingMoneyList.get(i)));
        }
        participants.add(dealer);
    }

    private void runGame() {
        receiveInitCardAllParticipants();
        if (isThereBlackjack(participants)) {
            return;
        }
        receiveMoreCardOrNotAllParticipants();
    }

    private void finishGame(){
        utill.printCardListAndScoreOfGameParticipant(participants);

    }

    private void receiveInitCardAllParticipants() {
        for (GameParticipant p : participants) {
           receiveInitCardEachParticipant(p);
        }
    }

    private void receiveInitCardEachParticipant(GameParticipant p){
        for(int i=0; i<2; i++) {
            p.addCard(dealer.giveCard());
        }
        utill.printCardListOfGameParticipant(p);
    }

    private boolean isThereBlackjack(ArrayList<GameParticipant> participants) {
        boolean blackjack = false;
        for (GameParticipant p : participants) {
            blackjack = (blackjack || p.isBlackjack());
        }
        return blackjack;
    }

    private void receiveMoreCardOrNotAllParticipants(){
        for(GameParticipant p : participants){
            receiveMoreCardOrNotEachParticipant(p);
        }

    }
    private void receiveMoreCardOrNotEachParticipant(GameParticipant p){
        p.addMoreCard(dealer.giveCard());
    }


}
