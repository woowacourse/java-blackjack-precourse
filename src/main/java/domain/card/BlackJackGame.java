package domain.card;

import domain.user.Player;

import java.util.*;

public class BlackJackGame {

    private static final int BIGGER_CASE_ALPHABET_A = 65;
    private static final int BIGGER_CASE_ALPHABET_Z = 90;
    private static final int LOWER_CASE_ALPHABET_A = 97;
    private static final int LOWER_CASE_ALPHABET_Z = 122;

    Scanner scanPlayerInfo = new Scanner(System.in);
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
        getBettingMoney();
        return players;
    }

    private int getBettingMoney() {
        String bettingMoney = scanPlayerInfo.nextLine();
        while(!isCorrectBettingMoney(bettingMoney)){
            bettingMoney = scanPlayerInfo.nextLine();
        }
        return Integer.parseInt(bettingMoney);
    }

    private boolean isCorrectBettingMoney(String bettingMoney) {
        try{
            int money = Integer.parseInt(bettingMoney);
            checkBettingMoney(money);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("만원 단위로 입력해주시기 바랍니다.");
        }
        return false;
    }

    private void checkBettingMoney(int bettingMoney) {
        if(bettingMoney % 10000 != 0) {
            throw new NumberFormatException();
        }
    }

    private List<String> getPlayersName() {
        List<String> playersName = Arrays.asList(scanPlayerInfo.nextLine().split(","));
        while (!isCorrectPlayerName(playersName)) {
            playersName = Arrays.asList(scanPlayerInfo.nextLine().split(","));
        }
        return playersName;
    }

    private boolean isCorrectPlayerName(List<String> playersName) {
        try {
            checkPlayerName(playersName);
            return true;
        } catch (Exception e) {
            System.out.println("에러");
        }
        return false;
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
        if(alphabet < BIGGER_CASE_ALPHABET_A || alphabet > BIGGER_CASE_ALPHABET_Z
                && alphabet < LOWER_CASE_ALPHABET_A || alphabet > LOWER_CASE_ALPHABET_Z) {
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
