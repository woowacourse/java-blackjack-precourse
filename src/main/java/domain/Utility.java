package domain;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class Utility {
    private final int BLACK_JACK_SCORE=21;

    public Utility() {
    }

    private String[] getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표로 구분)");
        Scanner sc = new Scanner(System.in);
        String names = sc.nextLine();
        String[] playerNames = names.split(",");
        return playerNames;
    }

    private int getBetting(String playerName) {
        System.out.println(playerName + "배팅 금액은?");
        Scanner sc = new Scanner(System.in);
        int bettingMoney = sc.nextInt();
        return bettingMoney;
    }

    private Player getPlayer(String personName) {
        int bettingMoney = this.getBetting(personName);
        Player player = new Player(personName, bettingMoney);
        return player;
    }

    public List<Player> getPlayers() {
        List<Player> playerList = new ArrayList();
        String[] playerNames = this.getPlayerNames();
        int playerNumber = playerNames.length;

        for(int i = 0; i < playerNumber; ++i) {
            Player player = this.getPlayer(playerNames[i]);
            playerList.add(player);
        }

        System.out.println("딜러와 " + playerNames + "에게 2장을 나누었습니다");
        return playerList;
    }

    public Player getHighestPlayer(List<Player>playerList){
        double highScore=0;
        Player highPlayer=null;

        for(Player player: playerList){
            int playerScore=player.getScoreSum();
            if(highScore<playerScore && BLACK_JACK_SCORE>=playerScore){
                highScore=playerScore;
                highPlayer=player;
            }
        }

        return highPlayer;
    }

    public boolean isPlayer(Dealer dealer,List<Player>playerList,Player highestPlayer){
        double dealerScore;
        highestPlayer=getHighestPlayer(playerList);
        double highestScore=highestPlayer.getScoreSum();

        dealerScore=dealer.getScoreSum();
        if(highestScore>dealerScore){
            return true;
        }
        return false;
    }

    public void getResult(Dealer dealer,List<Player>playerList){
        System.out.println("## 최종 수익");
        Player highestPlayer=null;
        System.out.println("딜러:10000");
        if(isPlayer(dealer,playerList,highestPlayer)){
            printResult(playerList,highestPlayer);
            return;
        }


        for(Player player:playerList){
            System.out.println(player.getName()+":-"+player.getMoney());
        }

    }

    public void printResult(List<Player>playerList,Player highestPlayer){
        System.out.println(highestPlayer.getName()+":"+highestPlayer.getMoney());
        for(Player player:playerList){
            if(player!=highestPlayer){
                System.out.println(player.getName()+":-"+player.getMoney());
            }
        }
    }

    public boolean isGameOver(Dealer dealer){
        if (dealer.getScoreSum() == BLACK_JACK_SCORE) {
            return true;
        }
        return false;
    }
}
