package domain.card;

import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {

    Scanner scanPlayersName = new Scanner(System.in);

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.startGame();
    }

    private void startGame() {
        List<Player> players = registPlayers();
    }

    private ArrayList<Player> registPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        List<String> playersName = getPlayersName();
        return players;
    }

    private List<String> getPlayersName() {
        List<String> playersName;
        while (true) {
            try {
                playersName = Arrays.asList(scanPlayersName.nextLine().split(","));
                checkPlayerName(playersName);
                break;
            } catch (Exception e) {
                System.out.println("에러");
            }
        }
        return playersName;
    }

    private void checkPlayerName(List<String> playersName) throws Exception {
        checkSamePlayersName(playersName);
        checkPlayersNameAlphabet(playersName);
    }

    private void checkPlayersNameAlphabet(List<String> playersName) throws Exception {
        for(String playerName : playersName){
            checkValidAlpabet(playerName);
        }
    }

    private void checkValidAlpabet(String playerName) throws Exception {
        for(char alphabet : playerName.toCharArray()) {
            isAlphabet(alphabet);
        }
    }

    private void isAlphabet(char alphabet) throws Exception {
        if(alphabet < 65 || alphabet > 90 && alphabet < 97 || alphabet > 122) {
            throw new Exception();
        }
    }

    private void checkSamePlayersName(List<String> playersName) throws Exception {
        for(int playerNameIndex = 0; playerNameIndex < playersName.size(); playerNameIndex++)  {
            nextPlayerName(playersName, playerNameIndex);
        }
    }

    private void nextPlayerName(List<String> playersName, int playerNameIndex) throws Exception {
        for(int playersNameIndexNext = playerNameIndex+1; playersNameIndexNext<playersName.size(); playersNameIndexNext++) {
            isSamePlayerName(playersName.get(playerNameIndex), playersName.get(playersNameIndexNext));
        }
    }

    private void isSamePlayerName(String s, String s1) throws Exception {
        if(s.toUpperCase().equals(s1.toUpperCase())) {
            throw new Exception();
        }
    }
}
