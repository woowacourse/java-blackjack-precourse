package domain.card;

import domain.user.Dealer;
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

    }

    private void displayBlackJackResult(List<Player> players, Dealer dealer) {
        int maxMoney = dealer.allScore();
        if(maxMoney >21) {
            displayPlayerWin(players);
        }
        if(maxMoney <= 21) {

        }
    }


    private void displayPlayerWin(List<Player> players) {
        System.out.println("딜러가 21을 초과 했으므로 모든 플레이어는 배팅 금액을 받습니다.");
        System.out.println("###최종수익");
        int loseMoney = 0;
        for(Player player : players) {
            System.out.println(player.getName() + " : " + player.getBettingMoney());
            loseMoney += player.getBettingMoney();
        }
        System.out.println("딜러 :" + (-loseMoney));
    }

    private void getdealerMoreCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Dealer dealer) {
        if(dealer.allScore()<17) {
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
            if(goOrStop.equals("n")) {
                System.out.println("카드를 그만 받습니다.");
                break;
            }
            if(player.allScore() > 21) {
                System.out.println("21을 초과 하였습니다. 다음 플레이어로 넘어갑니다");
                break;
            }
            if(player.allScore() == 21) {
                System.out.println("블랙잭!! 다음 플레이어로 넘어갑니다.");
                break;
            }
        }

    }

    private void addGetCard(HashMap<Card, Integer> useCard, List<Card> cardTrump, Player player,String goOrStop) {
        if(!goOrStop.equals("y") && !goOrStop.equals("n")){
            System.out.println("y 또는 n을 입력해주시기 바랍니다.");
        }
        if(goOrStop.equals("y")) {
            player.addCard(useCard, cardTrump);
            System.out.println(player.getName()+"카드:" +player.getCard().toString());
        }
    }


    private void firstTurnBlackJackOnlyPlayer(List<Player> players, Dealer dealer) {
        int winnerMoney = 0;
        int loseMoney = 0;
        for(Player player : players) {
            winnerMoney = getWinnerMoney(winnerMoney, player);
            loseMoney = getLoseMoney(loseMoney, player);
        }
        displayFirstTurnBlackJackOnlyPlayer(players,winnerMoney, loseMoney);
    }

    private int getLoseMoney(int loseMoney, Player player) {
        if(player.getCard().size() == 2 && player.allScore() != 21) {
            loseMoney += player.getBettingMoney();
        }
        return loseMoney;
    }

    private int getWinnerMoney(int winnerMoney, Player player) {
        if(player.getCard().size() == 2 && player.allScore() == 21) {
            winnerMoney += player.firstBlackJackWinnerMoney((int) player.getBettingMoney());
        }
        return winnerMoney;
    }

    private void displayFirstTurnBlackJackOnlyPlayer(List<Player> players, int winnerMoney, int loseMoney) {
        System.out.println("플레이어가 블랙잭에 당첨되었습니다!");
        System.out.println("###최종수익");
        for(Player player : players) {
            winnerOrLose(player);
        }
        System.out.println("딜러 :" + (loseMoney - winnerMoney));
    }

    private boolean checkBlackJack(List<Player> players, Dealer dealer) {
        for(Player player : players) {
            if(dealer.getCard().size() == 2 && dealer.allScore() == 21 && player.getCard().size() == 2 && player.allScore() == 21) {
                firstTurnBlackJackPlayerAndDealer(players, dealer);
                return true;
            }
            if(dealer.getCard().size() == 2 && dealer.allScore() != 21 && player.getCard().size() == 2 && player.allScore() == 21) {
                firstTurnBlackJackOnlyPlayer(players, dealer);
                return true;
            }
            if(dealer.getCard().size() == 2 && dealer.allScore() == 21 && player.getCard().size() == 2 && player.allScore() != 21){
                onlyDealerWin(players);
                return true;
            }
        }
        return false;
    }

    private void winnerOrLose(Player player) {
        if(player.allScore() == 21) {
            System.out.println(player.getName() + " : " + player.getBettingMoney()*1.5);
        }
        if(player.allScore() != 21) {
            System.out.println(player.getName() + " : "  + -player.getBettingMoney());
        }
    }

    private void firstTurnBlackJackPlayerAndDealer(List<Player> players, Dealer dealer) {

        for(Player player : players) {
            if(player.getCard().size() == 2 && player.allScore() == 21) {
                displayFirstTurnBlackJackPlayerAndDealer(players);
                break;
            }
        }

    }

    private void onlyDealerWin(List<Player> players) {
        int losePlayerValue =0;
        for(Player player : players) {
            if(player.getCard().size() == 2 && player.allScore() != 21) {
                losePlayerValue++;
            }
        }

        if(losePlayerValue == players.size()) {
            displayFirstTurnBlackJackOnlyDealer(players);
        }
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
        displayCardState(players,dealer);
        System.out.printf("\n\n");
    }

    private void displayCardState(List<Player> players, Dealer dealer) {
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
            System.out.printf("\n\n");
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
