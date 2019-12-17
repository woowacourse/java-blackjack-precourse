package domain.manager;

import domain.user.*;
import domain.card.*;

import java.util.*;

public class GameManager {

    private static final int initialNumOfCards = 2;
    private static final String delimiter = ",";
    private static final Scanner SC = new Scanner(System.in);

    private List<Player> players;
    private List<Card> cards;
    private Dealer dealer = new Dealer();
    private CardFactory cardFactory = new CardFactory();

    public static void main(String[] args) {
        GameManager game = new GameManager();
        game.startGame();
        game.playGame();
    }

    public void startGame() {
        List<String> playerNames = Arrays.asList(setPlayerNames());//player 이름 입력
        List<Double> bettingMoney = setBettingMoney(playerNames);//각 player의 배팅 금액 입력
        initializePlayers(playerNames, bettingMoney);
        cards = cardFactory.create();
        generateInitialCards();//딜러, 플레이어 각각 두장의 카드 발급
        //현재 상황 출력
    }

    public String[] setPlayerNames() {
        Scanner sc = SC;
        System.out.println("게임에 참여할 사람들의 이름을 입력하세요. (쉼표 기준으로 분리함)");
        String input = sc.nextLine();
        String[] playerNamesArray = input.split(delimiter);
        return playerNamesArray;
    }

    public List<Double> setBettingMoney(List<String> playerNames) {
        Scanner sc = SC;
        List<Double> bettingMoney = new List<Double>;
        for (String tmp : playerNames) {
            System.out.println(tmp + "의 배팅 금액은?");
            Double input = sc.nextDouble();
            bettingMoney.add(input);
        }
        return bettingMoney;
    }

    public void initializePlayers(List<String> playerNames, List<Double> bettingMoney) {
        for (int i = 0; i < playerNames.size(); i++) {
            Player p = new Player(playerNames.get(i), bettingMoney.get(i));
            players.add(p);
        }
    }

    public void generateInitialCards() {
        for (Player each : players) {
            cards = each.generateCards(initialNumOfCards, cards);
        }
        cards = dealer.generateCards(initialNumOfCards, cards);
        printInitialAnnouncement();
        printCurrentCards();
    }

    public void printInitialAnnouncement() {
        System.out.print("딜러와 ");
        List<String> playerNames = new ArrayList<>();
        for(Player each: players){
            playerNames.add(each.toString());
        }
        System.out.print(String.join(delimiter, playerNames));
        System.out.println("에게 2장씩 나누었습니다.");
    }

    public void printCurrentCards() {
        dealer.printNameAndCards();
        for (Player each : players) {
            each.printNameAndCards();
        }
        System.out.println();
    }


    public void playGame() {
        //각 player가 카드를 더 받을지
        //딜러가 카드를 더 받을
    }

}
