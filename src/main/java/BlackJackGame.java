import java.util.*;

import domain.gameElement.HandOutCard;
import domain.gameElement.NextProceedGame;
import domain.gameElement.PlayerBettingMoneyChecker;
import domain.gameElement.PlayersNaming;
import domain.gameElement.ProfitCalculation;
import domain.gameElement.UsersTotal;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class BlackJackGame {
    private List<User> users = new ArrayList<User>();
    private Scanner scanner = new Scanner(System.in);
    private HandOutCard handOutCard = new HandOutCard();
    private int step = 1;

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.playGame();
    }

    private void playGame() {
        boolean loop = false;
        startGame();
        endGame();
    }

    private void startGame() {
        users.add(new Dealer());
        List<String> playerNameList = playerNaming();
        for (String playerName : playerNameList) {
            users.add(new Player(playerName, playerSetBettingMoney(playerName)));
        }
        firstProceedGame();
    }

    private void firstProceedGame() {
        System.out.println("딜러와 " + getPlayerNamesALine() + "에게 2장의 카드를 나누었습니다.");
        FirstHandOutCards();
        if (new NextProceedGame().nextProceed(users, step) == true) {
            proceedGame();
        }
        
    }

    private void proceedGame() {
        step++;
        boolean NextTF = proceedHandOutCards();
        if (new NextProceedGame().nextProceed(users, step) == true) {
            proceedGame();
        }
    }

    private void endGame() {
        ProfitCalculation profitCalculation = new ProfitCalculation();
        UsersTotal usersTotal = new UsersTotal();
        profitCalculation.countProfit(users, step);
        usersTotal.usersTotalCards(users);
        usersTotal.usersTotalProfit(users);
    }

    private List<String> playerNaming() {
        String playerNames = "";
        List<String> playerNameList = new ArrayList<String>();
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리, 중복은 권장하지 않습니다.)");
            playerNames = scanner.nextLine().trim();
        } while (!new PlayersNaming().playerNamingCheck(playerNames));
        for (String player : playerNames.split(",")) {
            playerNameList.add(player.trim());
        }
        return playerNameList;
    }

    private double playerSetBettingMoney(String playerName) {
        String bettingMoney = "";
        PlayerBettingMoneyChecker playerBettingMoneyChecker = new PlayerBettingMoneyChecker();
        do {
            System.out.println(playerName + "의 배팅 금액은? (1원 단위로 쉼표없이 입력해주세요, 10억 미만으로만 가능합니다.)");
            bettingMoney = scanner.nextLine().trim();
        } while (!playerBettingMoneyChecker.playerBettingMoneyCheck(bettingMoney));
        return Double.parseDouble(bettingMoney);
    }

    private String getPlayerNamesALine() {
        StringBuilder playerNamesALineSB = new StringBuilder();
        for (int i = 1; i < users.size(); i++) {
            playerNamesALineSB.append(((Player) users.get(i)).getName());
            playerNamesALineSB.append(", ");
        }
        String playerNamesALine = playerNamesALineSB.toString();
        return playerNamesALine.substring(0, playerNamesALine.length() - 2);
    }

    private void FirstHandOutCards() {
        handOutCard.handOutCards((Dealer) users.get(0));
        handOutCard.handOutCards((Dealer) users.get(0));
        ((Dealer) users.get(0)).userCardsInfo(((Dealer) users.get(0)).getCards());
        for (int i = 1; i < users.size(); i++) {
            handOutCard.handOutCards((Player) users.get(i));
            handOutCard.handOutCards((Player) users.get(i));
            users.get(i).userCardsInfo(((Player) users.get(i)).getCards(), ((Player) users.get(i)).getName());
        }
    }

    private boolean proceedHandOutCards() {
        Set<Boolean> finishTFSet = new HashSet<Boolean>();
        for (int i = 1; i < users.size(); i++) {
            finishTFSet.add(askHandOutCards((Player) users.get(i)));
            users.get(i).userCardsInfo(((Player) users.get(i)).getCards(), ((Player) users.get(i)).getName());
        }
        if (step == 2) {
            System.out.println("딜러는 16이하라 카드 한 장을 더 받았습니다.");
            handOutCard.handOutCards((Dealer) users.get(0));
        }
        return finishTFSet.contains(true);
    }

    private boolean askHandOutCards(Player player) {
        System.out.println(player.getName() + "(은)는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String cardHandOut = "";
        do {
            cardHandOut = scanner.nextLine();
        } while (!cardHandOut.equals("y") && !cardHandOut.equals("n"));
        if (cardHandOut.equals("y")) {
            handOutCard.handOutCards(player);
            return true;
        }
        return false;
    }

}
