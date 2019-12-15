package domain;

import domain.blackjackgame.BlackjackGame;
import domain.user.Player;
import domain.user.Players;
import view.inputView;

import java.util.List;

import static utility.PlayerUtility.generatePlayers;

public class PlayBlackjackGame {
    public static void main(String[] args) {
        Players players = generatePlayers();
        BlackjackGame blackjackGame = new BlackjackGame(players);
        blackjackGame.execute();

    }

}
