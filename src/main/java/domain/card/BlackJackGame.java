package domain.card;

import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class BlackJackGame {

    private static final int BIGGER_CASE_ALPHABET_A = 65;
    private static final int BIGGER_CASE_ALPHABET_Z = 90;
    private static final int LOWER_CASE_ALPHABET_A = 97;
    private static final int LOWER_CASE_ALPHABET_Z = 122;
    private static final int BLACKJACK = 21;
    private static final int DEALER_GET_MORE_CARD_SCORE = 17;
    private static final int FIRST_TURN = 2;
    private static final int FIRST_PLAYER_INDEX = 0;
    private static final int LEAST_BETTING_MONEY = 10000;
    private static final int LEAST_BETTING_MONEY_SIZE = 1;
    private static final int LEAST_BETTING_MONEY_UNIT = 0;
    private static final int NEXT_PLAYER_NAME_INDEX = 1;
    private static final String GET_MORE_CARD = "y";
    private static final String STOP_GET_MORE_CARD = "n";


    Scanner scanPlayerInfo = new Scanner(System.in);

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.startGame();
    }

    private void startGame() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요 (쉼표로 구분합니다)");
        List<Player> players = registPlayers();
        Dealer dealer = new Dealer();
        CardFactory cardFactory = new CardFactory();
        List<Card> cardTrump = cardFactory.create();
        playBlackJackGame(players, dealer, cardTrump);
    }

    private boolean playBlackJackGame(List<Player> players, Dealer dealer, List<Card> cardTrump) {
        HashMap<Card, Integer> useCard = new HashMap<>();
        if(getCardToFirstTurn(useCard, players, dealer, cardTrump)) {
            return true;
        }
        takeMoreCard(players, dealer, useCard, cardTrump);
        return false;
    }

    private void takeMoreCard(List<Player> players, Dealer dealer, HashMap<Card, Integer> useCard, List<Card> cardTrump) {
        for(Player player : players) {
            duringGetCard(useCard, cardTrump, player);
        }
        System.out.printf("\n\n");
        getdealerMoreCard(useCard,cardTrump,dealer);
        displayBlackJackResult(players, dealer);
    }

    private void displayBlackJackResult(List<Player> players, Dealer dealer) {
        int maxMoney = dealer.allScore();

        if(maxMoney > BLACKJACK) {
            displayPlayerWin(players);
        }

        if(maxMoney <= BLACKJACK) {
            displayResult(players, dealer, maxMoney);
        }
    }

    private void displayResult(List<Player> players, Dealer dealer, int maxScore) {
        System.out.println("딜러 카드: " + dealer.getCard().toString() + "- 결과: " + dealer.allScore());
        for(Player player : players) {
            maxScore = findMaxScore(maxScore, player);
            System.out.println(player.getName() + "카드 : " + player.getCard().toString() + "- 결과: " + player.allScore());
        }
        System.out.printf("\n\n");
        displayDealerOrPlayerWinCase(players, dealer, maxScore);
    }

    private int findMaxScore(int maxScore, Player player) {
        if(maxScore < player.allScore() && player.allScore() <= BLACKJACK) {
            maxScore = player.allScore();
        }
        return maxScore;
    }

    private void displayDealerOrPlayerWinCase(List<Player> players, Dealer dealer, int maxScore) {
        for(Player player: players) {
            if(dealer.allScore() == maxScore && player.allScore() == maxScore) {
                displayBlackJack(players, maxScore);
                break;
            }
            if(dealer.allScore() != maxScore && player.allScore() == maxScore) {
                displayBlackJack(players, maxScore);
                break;
            }
            if(dealer.allScore() == maxScore && player.allScore() != maxScore) {
                displayBlackJackDealer(players);
                break;
            }
        }
    }

    private boolean checkBlackJack(List<Player> players, Dealer dealer) {
        for(Player player : players) {
            if(dealer.getCard().size() == FIRST_TURN && dealer.allScore() == BLACKJACK
                    && player.getCard().size() == FIRST_TURN && player.allScore() == BLACKJACK) {
                firstTurnBlackJackPlayerAndDealer(players);
                return true;
            }
            if(dealer.getCard().size() == FIRST_TURN && dealer.allScore() != BLACKJACK
                    && player.getCard().size() == FIRST_TURN && player.allScore() == BLACKJACK) {
                firstTurnBlackJackOnlyPlayer(players);
                return true;
            }
            if(dealer.getCard().size() == FIRST_TURN && dealer.allScore() == BLACKJACK
                    && player.getCard().size() == FIRST_TURN && player.allScore() != BLACKJACK){
                onlyDealerWin(players);
                return true;
            }
        }
        return false;
    }

    private void displayBlackJackDealer(List<Player> players) {
        int dealerMoney = 0;
        System.out.println("###최종수익");
        for(Player player : players) {
            System.out.println(player.getName() + " : " + (-player.getBettingMoney()));
            dealerMoney += player.getBettingMoney();
        }
        System.out.println("딜러 : " + dealerMoney);

    }

    private void displayBlackJack(List<Player> players, int maxScore) {
        int winnerMoney = 0;
        int loseMoney = 0;
        System.out.println("###최종수익");
        for(Player player : players) {
            if(player.allScore() == maxScore) {
                System.out.println(player.getName() + " : " + (int)player.getBettingMoney());
                winnerMoney += player.getBettingMoney();
            }
            if(player.allScore() != maxScore) {
                System.out.println(player.getName() + " : " + (int)(-player.getBettingMoney()));
                loseMoney += player.getBettingMoney();
            }

        }
        System.out.println("딜러 : " + (loseMoney - winnerMoney));
    }

    private void displayPlayerWin(List<Player> players) {
        System.out.println("딜러가 21을 초과 했으므로 모든 플레이어는 배팅 금액을 받습니다.");
        System.out.println("###최종수익");
        int loseMoney = 0;
        for(Player player : players) {
            System.out.println(player.getName() + " : " + (int)player.getBettingMoney());
            loseMoney += player.getBettingMoney();
        }
        System.out.println("딜러 :" + (-loseMoney));
    }

    private void getdealerMoreCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Dealer dealer) {
        if(dealer.allScore() <= DEALER_GET_MORE_CARD_SCORE) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
            System.out.printf("\n\n");
            dealer.addCard(useCard,cardTrump);
        }
    }

    private void duringGetCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Player player) {
        while(true) {
            System.out.println(player.getName()+ "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
            String goOrStop = scanPlayerInfo.nextLine();
            addGetCard(useCard,cardTrump,player,goOrStop);
            if(goOrStop.equals(STOP_GET_MORE_CARD)) {
                System.out.println("카드를 그만 받습니다.");
                break;
            }
            if(player.allScore() > BLACKJACK) {
                System.out.println("21을 초과 하였습니다. 다음 플레이어로 넘어갑니다");
                break;
            }
            if(player.allScore() == BLACKJACK) {
                System.out.println("블랙잭!! 다음 플레이어로 넘어갑니다.");
                break;
            }
        }
    }

    private void addGetCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Player player,String goOrStop) {
        if(!goOrStop.equals(GET_MORE_CARD) && !goOrStop.equals(STOP_GET_MORE_CARD)){
            System.out.println("y 또는 n을 입력해주시기 바랍니다.");
        }
        if(goOrStop.equals(GET_MORE_CARD)) {
            player.addCard(useCard, cardTrump);
            System.out.println(player.getName()+"카드:" +player.getCard().toString());
        }
    }


    private void firstTurnBlackJackOnlyPlayer(List<Player> players) {
        int winnerMoney = 0;
        int loseMoney = 0;
        System.out.println("###최종수익");
        for(Player player : players) {
            winnerMoney = getWinnerMoney(winnerMoney, player);
            loseMoney = getLoseMoney(loseMoney, player);
        }
        displayFirstTurnBlackJackOnlyPlayer(players,winnerMoney, loseMoney);
    }

    private void displayFirstTurnBlackJackOnlyPlayer(List<Player> players, int winnerMoney, int loseMoney) {
        System.out.println("플레이어가 블랙잭에 당첨되었습니다!");
        System.out.println("###최종수익");
        for(Player player : players) {
            winnerOrLose(player);
        }
        System.out.println("딜러 :" + (loseMoney - winnerMoney));
    }

    private void winnerOrLose(Player player) {
        if(player.allScore() == BLACKJACK) {
            System.out.println(player.getName() + " : " + player.firstBlackJackWinnerMoney((int)player.getBettingMoney()));
        }
        if(player.allScore() != BLACKJACK) {
            System.out.println(player.getName() + " : "  + (int)-player.getBettingMoney());
        }
    }

    private int getLoseMoney(int loseMoney, Player player) {
        if(player.getCard().size() == FIRST_TURN && player.allScore() != BLACKJACK) {
            loseMoney += player.getBettingMoney();
        }
        return loseMoney;
    }

    private int getWinnerMoney(int winnerMoney, Player player) {
        if(player.getCard().size() == FIRST_TURN && player.allScore() == BLACKJACK) {
            winnerMoney += player.firstBlackJackWinnerMoney((int) player.getBettingMoney());
        }
        return winnerMoney;
    }

    private void firstTurnBlackJackPlayerAndDealer(List<Player> players) {
        for(Player player : players) {
            if(player.getCard().size() == FIRST_TURN && player.allScore() == BLACKJACK) {
                displayFirstTurnBlackJackPlayerAndDealer(players);
                break;
            }
        }
    }

    private void onlyDealerWin(List<Player> players) {
        int losePlayerValue =0;
        for(Player player : players) {
            losePlayerValue = getLosePlayerValue(losePlayerValue, player);
        }
        checkAllPlayerLoseToDealer(players, losePlayerValue);
    }

    private void checkAllPlayerLoseToDealer(List<Player> players, int losePlayerValue) {
        if(losePlayerValue == players.size()) {
            displayFirstTurnBlackJackOnlyDealer(players);
        }
    }

    private int getLosePlayerValue(int losePlayerValue, Player player) {
        if(player.getCard().size() == FIRST_TURN && player.allScore() != BLACKJACK) {
            losePlayerValue++;
        }
        return losePlayerValue;
    }

    private void displayFirstTurnBlackJackOnlyDealer(List<Player> players) {
        System.out.println("딜러가 승리 하였습니다!");
        System.out.println("###최종수익");
        int loseMoney =0;
        for(Player player : players){
            loseMoney += player.getBettingMoney();
            System.out.println(player.getName() + " : " + (-player.getBettingMoney()));
        }
        System.out.println("딜러: " + loseMoney);
    }

    private void displayFirstTurnBlackJackPlayerAndDealer(List<Player> players) {
        int dealerLoseMoney = 0;
        System.out.println("딜러와 플레이어가 블랙잭이므로 플레이어는 딜러에게 배팅금액을 받습니다.");
        System.out.println("#최종수익");
        for(Player player : players) {
            dealerLoseMoney += player.getBettingMoney();
            System.out.println(player.getName() + ": " + player.getBettingMoney());
        }
        System.out.println("딜러:" + -dealerLoseMoney);
    }

    private boolean getCardToFirstTurn(HashMap<Card, Integer> useCard, List<Player> players, Dealer dealer, List<Card> cardTrump) {
        for(int i=0; i<2; i++) {
            giveCardToAllPlayer(useCard, players, dealer, cardTrump);
        }
        displayFirstTurn(players, dealer);
        if(checkBlackJack(players,dealer)) {
            return true;
        }
        return false;
    }

    private void displayFirstTurn(List<Player> players, Dealer dealer) {
        System.out.print("딜러와 ");
        for(Player player : players) {
            System.out.print(player.getName() + " ");
        }
        System.out.println("에게 2장의 카드를 나누었습니다.");
        System.out.println("딜러 : " + dealer.getCard().get(0));
        displayCardState(players);
        System.out.printf("\n\n");
    }

    private void displayCardState(List<Player> players) {
        for(Player player: players) {
            System.out.print(player.getName() + " : ");
            player.displayCardState();
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
            System.out.printf("\n");
            System.out.println(playerName + "의 베팅 금액은?");
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
            System.out.println("만원 단위로 입력해주시기 바랍니다.");
        }
        return false;
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
