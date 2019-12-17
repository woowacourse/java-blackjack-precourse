package domain.card;

import domain.display.Display;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class BlackJackGame {

    private static final int UPPER_CASE_ALPHABET_A = 65;
    private static final int UPPER_CASE_ALPHABET_Z = 90;
    private static final int LOWER_CASE_ALPHABET_A = 97;
    private static final int LOWER_CASE_ALPHABET_Z = 122;
    private static final int BLACKJACK = 21;
    private static final int DEALER_GET_MORE_CARD_SCORE = 17;
    private static final int FIRST_PLAYER_INDEX = 0;
    private static final int LEAST_BETTING_MONEY = 10000;
    private static final int LEAST_BETTING_MONEY_SIZE = 1;
    private static final int LEAST_BETTING_MONEY_UNIT = 0;
    private static final int NEXT_PLAYER_NAME_INDEX = 1;
    private static final int FIRST_TURN_CARD_SIZE = 2;
    private static final String GET_MORE_CARD = "y";
    private static final String STOP_GET_MORE_CARD = "n";

    private Scanner scanPlayerInfo = new Scanner(System.in);
    private Display display = new Display();

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.startGame();
    }

    private void startGame() {
        display.displayStartGame();
        List<Player> players = registPlayers();
        Dealer dealer = new Dealer();
        List<Card> cardTrump = CardFactory.create();
        playBlackJackGame(players, dealer, cardTrump);
    }


    private void playBlackJackGame(List<Player> players, Dealer dealer, List<Card> cardTrump) {
        HashMap<Card, Integer> useCard = new HashMap<>();
        if(getCardToFirstTurn(useCard, players, dealer, cardTrump)) {
            return;
        }
        takeMoreCard(players, dealer, useCard, cardTrump);
    }

    private boolean getCardToFirstTurn(HashMap<Card, Integer> useCard, List<Player> players, Dealer dealer, List<Card> cardTrump) {
        for(int i=FIRST_PLAYER_INDEX; i<FIRST_TURN_CARD_SIZE; i++) {
            giveCardToAllPlayer(useCard, players, dealer, cardTrump);
        }
        display.displayFirstTurn(players, dealer);

        if(checkFirstTurnBlackJack(players,dealer)) {
            return true;
        }
        return false;
    }

    private boolean checkFirstTurnBlackJack(List<Player> players, Dealer dealer) {
        if(findFirstTurnWinCase(players, dealer)) {
            return true;
        }
        return false;
    }

    private boolean findFirstTurnWinCase(List<Player> players, Dealer dealer) {
        for(Player player : players) {
            if(isFirstTurnPlayerWin(dealer, player)) {
                display.firstTurnBlackJackOnlyPlayer(players);
                return true;
            }
            if(isFirstTurnTie(dealer, player)) {
                display.displayTie(players,BLACKJACK);
                return true;
            }
            if(isFirstTurnDealerWin(dealer, player)) {
                display.displayBlackJackDealer(players);
                return true;
            }
        }
        return false;
    }

    private void takeMoreCard(List<Player> players, Dealer dealer, HashMap<Card, Integer> useCard, List<Card> cardTrump) {
        for(Player player : players) {
            duringGetCard(useCard, cardTrump, player);
        }
        System.out.printf("\n\n");
        getDealerMoreCard(useCard,cardTrump,dealer);
        display.displayBlackJackResult(players, dealer);
    }

    private boolean isFirstTurnPlayerWin(Dealer dealer, Player player) {
        return dealer.allScore() != BLACKJACK && dealer.getCard().size() == FIRST_TURN_CARD_SIZE
                && player.allScore() == BLACKJACK && player.getCard().size() == FIRST_TURN_CARD_SIZE;
    }

    private boolean isFirstTurnTie(Dealer dealer, Player player) {
        return dealer.allScore() == BLACKJACK && dealer.getCard().size() == FIRST_TURN_CARD_SIZE
                && player.allScore() == BLACKJACK && player.getCard().size() == FIRST_TURN_CARD_SIZE;
    }

    private boolean isFirstTurnDealerWin(Dealer dealer, Player player) {
        return dealer.allScore() == BLACKJACK && dealer.getCard().size() == FIRST_TURN_CARD_SIZE
                && player.allScore() != BLACKJACK && player.getCard().size() == FIRST_TURN_CARD_SIZE;
    }

    private void getDealerMoreCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Dealer dealer) {
        if(dealer.allScore() <= DEALER_GET_MORE_CARD_SCORE) {
            display.displayDealerGetMoreCard();
            System.out.printf("\n\n");
            dealer.addCard(useCard,cardTrump);
        }
    }

    private void duringGetCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Player player) {
        boolean stopTakeCardFlag = true;
        while(stopTakeCardFlag) {
            display.displayGetMoreCard(player);
            String goOrStop = scanPlayerInfo.nextLine();
            addGetCard(useCard,cardTrump,player,goOrStop);
            stopTakeCardFlag = isStopTakeCard(player, goOrStop);
        }
    }

    private boolean isStopTakeCard(Player player, String goOrStop) {
        if(isStopTakeMoreCard(goOrStop) || isOverBlackJack(player) || isBlackJack(player)) {
            return false;
        }
        return true;
    }

    private boolean isStopTakeMoreCard(String goOrStop) {
        if(goOrStop.equals(STOP_GET_MORE_CARD)) {
            display.displayStopGetCard();
            return true;
        }
        return false;
    }


    private boolean isOverBlackJack(Player player) {
        if(player.allScore() > BLACKJACK) {
            displayOverBlackJack();
            return true;
        }
        return false;
    }

    private void displayOverBlackJack() {
        System.out.println("21을 초과 하였습니다. 다음 플레이어로 넘어갑니다");
    }

    private boolean isBlackJack(Player player) {
        if(player.allScore() == BLACKJACK) {
            display.displayBlackJack();
            return true;
        }
        return false;
    }

    private void addGetCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Player player,String goOrStop) {
        if(!goOrStop.equals(GET_MORE_CARD) && !goOrStop.equals(STOP_GET_MORE_CARD)){
            display.displayInputYesOrNo();
        }
        if(goOrStop.equals(GET_MORE_CARD)) {
            player.addCard(useCard, cardTrump);
            display.displayPlayerCards(player);
        }
    }


    private void giveCardToAllPlayer(HashMap<Card, Integer> useCard, List<Player> players, Dealer dealer, List<Card> cardTrump) {
        for(Player player : players) {
            player.addCard(useCard, cardTrump);
        }
        dealer.addCard(useCard,cardTrump);
    }

    private ArrayList<Player> registPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        List<String> playersName = getPlayersName();
        readyToGame(players, playersName);
        return players;
    }

    private void readyToGame(ArrayList<Player> players, List<String> playersName) {
        for(String playerName : playersName) {
            display.displayGetBettingMoney(playerName);
            playerJoinGame(players, playerName);
        }
    }


    private void playerJoinGame(List<Player> players, String playerName) {
        players.add(new Player(playerName, getBettingMoney()));
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
            displayGetMoneyErrorMessage();
        }
        return false;
    }

    private void displayGetMoneyErrorMessage() {
        System.out.println("만원 단위로 입력해주시기 바랍니다.");
    }

    private void checkBettingMoney(int bettingMoney) {
        if(bettingMoney % LEAST_BETTING_MONEY != LEAST_BETTING_MONEY_UNIT
                || bettingMoney / LEAST_BETTING_MONEY < LEAST_BETTING_MONEY_SIZE) {
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
            display.displayNameErrorMessage();
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
        if(alphabet < UPPER_CASE_ALPHABET_A || alphabet > UPPER_CASE_ALPHABET_Z
                && alphabet < LOWER_CASE_ALPHABET_A || alphabet > LOWER_CASE_ALPHABET_Z) {
            throw new Exception();
        }
    }

    private void checkSamePlayersName(List<String> playersName) throws Exception {
        for(int playerNameIndex = FIRST_PLAYER_INDEX; playerNameIndex < playersName.size(); playerNameIndex++)  {
            nextPlayerName(playersName, playerNameIndex);
        }
    }

    private void nextPlayerName(List<String> playersName, int playerNameIndex) throws Exception {
        for(int playersNameIndexNext = playerNameIndex+NEXT_PLAYER_NAME_INDEX;
                playersNameIndexNext < playersName.size(); playersNameIndexNext++) {
            isSamePlayerName(playersName.get(playerNameIndex), playersName.get(playersNameIndexNext));
        }
    }

    private void isSamePlayerName(String playerName, String anotherPlayerName) throws Exception {
        if(playerName.toUpperCase().equals(anotherPlayerName.toUpperCase())) {
            throw new Exception();
        }
    }
}
