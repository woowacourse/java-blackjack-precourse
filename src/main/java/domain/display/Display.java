package domain.display;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class Display {

    private static final int BLACKJACK = 21;
    private static final int DEALER_FIRST_CARD_INDEX = 0;
    private static final int FIRST_TURN = 2;

    public void displayBlackJackResult(List<Player> players, Dealer dealer) {
        int maxMoney = dealer.allScore();

        if(maxMoney > BLACKJACK) {
            displayPlayerWin(players);
        }

        if(maxMoney <= BLACKJACK) {
            displayResult(players, dealer, maxMoney);
        }
    }

    public void firstTurnBlackJackOnlyPlayer(List<Player> players) {
        int winnerMoney = 0;
        int loserMoney = 0;
        for(Player player : players) {
            winnerMoney = getFirstTurnWinnerMoney(winnerMoney, player);
            loserMoney = getFirstTurnLoserMoney(loserMoney, player);
        }
        displayFirstTurnBlackJackOnlyPlayer(players,winnerMoney, loserMoney);
    }

    private int getFirstTurnWinnerMoney(int winnerMoney, Player player) {
        if(player.getCard().size() == FIRST_TURN && player.allScore() == BLACKJACK) {
            winnerMoney += player.firstBlackJackWinnerMoney((int) player.getBettingMoney());
        }

        return winnerMoney;
    }

    private int getFirstTurnLoserMoney(int loserMoney, Player player) {
        if(player.getCard().size() == FIRST_TURN && player.allScore() != BLACKJACK) {
            loserMoney += player.getBettingMoney();
        }

        return loserMoney;
    }


    private void displayFirstTurnBlackJackOnlyPlayer(List<Player> players, int winnerMoney, int loseMoney) {
        System.out.println("플레이어가 1회차에 블랙잭에 당첨되었습니다!");
        displayResult();
        for(Player player : players) {
            winnerOrLose(player);
        }
        System.out.println("딜러 :" + (loseMoney - winnerMoney));
    }

    private void displayResult() {
        System.out.println("###최종수익");
    }

    private void winnerOrLose(Player player) {
        if(player.allScore() == BLACKJACK) {
            System.out.println(player.getName() + ": "
                    + player.firstBlackJackWinnerMoney((int) player.getBettingMoney()));
        }

        if(player.allScore() != BLACKJACK) {
            System.out.println(player.getName() + ": "
                    + (int)-player.getBettingMoney());
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

    public void displayFirstTurn(List<Player> players, Dealer dealer) {
        System.out.print("딜러와 ");
        for(Player player : players) {
            System.out.print(player.getName() + " ");
        }
        System.out.println("에게 2장의 카드를 나누었습니다.");
        System.out.println("딜러 : " + dealer.getCard().get(DEALER_FIRST_CARD_INDEX));
        displayCardState(players);
        System.out.printf("\n\n");
    }

    private void displayCardState(List<Player> players) {
        for(Player player: players) {
            System.out.print(player.getName() + ": ");
            player.displayCardState();
        }
    }

    private void displayDealerOrPlayerWinCase(List<Player> players, Dealer dealer, int maxScore) {
        boolean endGameFlag = true;
        int playersIndex = 0;
        while(endGameFlag) {
            endGameFlag = validateGameResultAndDisplay(players, dealer, maxScore, playersIndex);
            playersIndex++;
        }
    }

    private boolean validateGameResultAndDisplay(List<Player> players, Dealer dealer, int maxScore,int playersIndex) {
        if(playersIndex == players.size()) {
            return false;
        }

        if(dealer.allScore() != maxScore && players.get(playersIndex).allScore() != maxScore) {
            return true;
        }

        findWinner(players, dealer, maxScore, playersIndex);
        return false;
    }

    private void findWinner(List<Player> players, Dealer dealer, int maxScore, int playersIndex) {
        if(dealer.allScore() == maxScore && players.get(playersIndex).allScore() == maxScore) {
            displayTie(players,maxScore);
        }
        if(dealer.allScore() != maxScore && players.get(playersIndex).allScore() == maxScore) {
            displayBlackJack(players, maxScore);
        }
        if(dealer.allScore() == maxScore && players.get(playersIndex).allScore() != maxScore) {
            displayBlackJackDealer(players);
        }
    }

    public void displayTie(List<Player> players, int maxScore) {
        int loseMoney = 0;
        System.out.println("플레이어와 딜러가 둘다 블랙잭이므로 블랙잭인 플레이어는 배당금을 돌려받습니다.");
        displayResult();
        for(Player player : players) {
            drawnPlayerAndDealer(maxScore, player);
            loseMoney = getLoseMoney(maxScore, loseMoney, player);
        }
        System.out.println("딜러 : 0");
    }

    private void drawnPlayerAndDealer(int maxScore, Player player) {
        if(player.allScore() == maxScore) {
            System.out.println(player.getName() + " : 0");
        }
    }

    private int getLoseMoney(int maxScore, int loseMoney, Player player) {
        if(player.allScore() != maxScore) {
            System.out.println(player.getName() + " : " + (int) (-player.getBettingMoney()));
            loseMoney += player.getBettingMoney();
        }
        return loseMoney;
    }

    private void displayBlackJack(List<Player> players, int maxScore) {
        int winnerMoney = 0;
        int loseMoney = 0;
        displayResult();
        for(Player player : players) {
            winnerMoney = getWinnerMoney(maxScore, winnerMoney, player);
            loseMoney = getLoseMoney(maxScore, loseMoney, player);
        }
        System.out.println("딜러 : " + (loseMoney - winnerMoney));
    }

    private int getWinnerMoney(int maxScore, int winnerMoney, Player player) {
        if(player.allScore() == maxScore) {
            System.out.println(player.getName() + " : " + (int) player.getBettingMoney());
            winnerMoney += player.getBettingMoney();
        }
        return winnerMoney;
    }

    private void displayPlayerWin(List<Player> players) {
        System.out.println("딜러가 21을 초과 했으므로 모든 플레이어는 배팅 금액을 받습니다.");
        displayResult();
        int loseMoney = 0;
        for(Player player : players) {
            System.out.println(player.getName() + " : " + (int)player.getBettingMoney());
            loseMoney += player.getBettingMoney();
        }
        System.out.println("딜러 :" + (-loseMoney));
    }

    public void displayBlackJackDealer(List<Player> players) {
        int dealerMoney = 0;
        displayResult();
        for(Player player : players) {
            System.out.println(player.getName() + " : " + (int) (-player.getBettingMoney()));
            dealerMoney += player.getBettingMoney();
        }
        System.out.println("딜러 : " + dealerMoney);
    }

    public void displayStartGame() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요 (쉼표로 구분합니다)");
    }

    public void displayDealerGetMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
    }

    public void displayGetMoreCard(Player player) {
        System.out.println(player.getName()+ "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
    }

    public void displayStopGetCard() {
        System.out.println("카드를 그만 받습니다.");
    }

    public void displayBlackJack() {
        System.out.println("블랙잭!! 다음 플레이어로 넘어갑니다.");
    }

    public void displayPlayerCards(Player player) {
        System.out.println(player.getName()+"카드:" +player.getCard().toString());
    }

    public void displayInputYesOrNo() {
        System.out.println("y 또는 n을 입력해주시기 바랍니다.");
    }

    public void displayGetBettingMoney(String playerName) {
        System.out.printf("\n");
        System.out.println(playerName + "의 베팅 금액은?");
    }

    public void displayNameErrorMessage() {
        System.out.println("이름은 영문자 (대소문자 구별x)만 가능합니다.");
    }

    public void displayGetMoneyErrorMessage() {
        System.out.println("만원 단위로 입력해주시기 바랍니다.");
    }
}
