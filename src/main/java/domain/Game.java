package domain;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.*;

public class Game {
    private static ArrayList<Player> players = new ArrayList<>();
    private static Dealer dealer = new Dealer();
    private static Queue<Card> deck = new LinkedList<>();
    private static HashMap<User, Double> prize = new HashMap<>();
    private static HashMap<User, Boolean> gameEndFlag = new HashMap<>();

    public static final int NUM_OF_SYMBOL = 13;
    public static final int NUM_OF_TYPE = 4;
    public static final int NUM_OF_FIRST_CARD = 2;
    public static final int DRAW_FLAG = 16;
    public static final int BLACK_JACK = 21;

    public static void main(String[] args) {
        ArrayList<String> names = makePlayers();
        makeShuffleDeck();
        drawFirstTwoCards(names);
        drawMoreCard();
        dealer.firstDecision();
        finalGameStart();
        finalResult();
    }

    /* Operation Part */

    public static void finalGameStart() {
        for (Player player : players) {
            finalGame(player);
        }
    }

    public static void finalGame(Player player) {
        if ((!gameEndFlag.get(player)) && ((dealer.getMinimumSum() > BLACK_JACK) || player.getAceSum() > dealer.getAceSum())) {
            playerWin(player);
            return;
        }
        if (!gameEndFlag.get(player) && dealer.getAceSum() == player.getAceSum()) {
            playerDraw(player);
            return;
        }
        if (gameEndFlag.get(player)) return;
        playerLose(player);
    }


    public static void finalResult() {
        dealer.printNameAndCardsAndSum();
        for (Player player : players) {
            player.printNameAndCardsAndSum();
        }
        printFinalResult();
    }


    public static void playerLose(Player player) {
        double dealerPrize = prize.get(dealer);

        dealerPrize += player.getBettingMoney();
        prize.put(player, player.getBettingMoney() * (-1.0));
        prize.put(dealer, dealerPrize);
        gameEndFlag.put(player, true);
    }

    public static void playerDraw(Player player) {
        prize.put(player, 0.0);
        gameEndFlag.put(player, true);
    }

    public static void playerWin(Player player) {
        double dealerPrize = prize.get(dealer);

        dealerPrize -= player.getBettingMoney();
        prize.put(dealer, dealerPrize);
        prize.put(player, player.getBettingMoney());
        gameEndFlag.put(player, true);
    }

    public static void playerWinByBlackJack(Player player) {
        double dealerPrize = prize.get(dealer);

        dealerPrize -= 1.5 * player.getBettingMoney();
        prize.put(dealer, dealerPrize);
        prize.put(player, player.getBettingMoney() * 1.5);
        gameEndFlag.put(player, true);
    }


    public static void drawMoreCard() {
        for (Player player : players) {
            doYouDraw(player);
        }
    }

    public static void doYouDraw(Player player) {
        boolean choiceFlag = false;
        while (player.getAceSum() != BLACK_JACK && player.getMinimumSum()
                < BLACK_JACK && !choiceFlag) {
            boolean answer = printWhetherDraw(player);
            choiceFlag = makeDrawChoice(player, answer);
        }
        if (player.getMinimumSum() > BLACK_JACK) {
            playerLose(player);
        }
    }

    public static boolean makeDrawChoice(Player player, boolean answer) {
        if (answer) {
            drawFromDeck(player, 1);
            player.printNameAndCards();
            return false;
        }
        player.printNameAndCards();
        return true;
    }


    public static void twoCardsBlackJack(Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            playerDraw(player);
            return;
        }
        if (player.isBlackJack()) {
            playerWinByBlackJack(player);
        }
    }

    public static void drawFirstTwoCards(ArrayList<String> names) {
        drawFromDeck(dealer, NUM_OF_FIRST_CARD);
        for (Player player : players) {
            drawFromDeck(player, NUM_OF_FIRST_CARD);
            twoCardsBlackJack(player);
        }
        printFirstDrawResult(names);
    }


    public static void drawFromDeck(User user, int numOfCards) {
        for (int i = 0; i < numOfCards; i++) {
            Card card = deck.poll();
            checkDeckEmpty(card);
            user.addCard(card);
        }
    }

    public static void makeShuffleDeck() {
        LinkedHashSet<Card> deckSet = new LinkedHashSet<>();

        while (deckSet.size() != NUM_OF_TYPE * NUM_OF_SYMBOL) {
            deckSet.add(Card.makeRandomCard());
        }
        Iterator<Card> iterator = deckSet.iterator();
        while (iterator.hasNext()) {
            deck.offer(iterator.next());
        }
    }

    public static ArrayList<String> makePlayers() {
        ArrayList<String> names = takePlayerName();
        ArrayList<Double> betting = takePlayerBetting(names);
        checkInput(names, betting);
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), betting.get(i)));
        }
        setPrize();
        setGameEndFlag();
        return names;
    }

    public static void setGameEndFlag() {
        gameEndFlag.put(dealer, false);
        for (Player player : players) {
            gameEndFlag.put(player, false);
        }
    }

    public static void setPrize() {
        double sum = 0.0;

        prize.put(dealer, sum);
        for (Player player : players) {
            prize.put(player, sum);
        }
    }

    /* UI PART */

    public static void printFinalResult() {
        System.out.println("\n## 최종 수익");
        System.out.println(dealer + " : " + Math.round(prize.get(dealer)));
        for (Player player : players) {
            System.out.println(player + " : " + Math.round(prize.get(player)));
        }
    }

    public static boolean printWhetherDraw(Player player) {
        Scanner s = new Scanner(System.in);
        String answer;

        System.out.println("\n" + player + "는 한장의 카드를 더 받겠습니까?(예는 y," +
                " 아니오는 n)");
        answer = s.nextLine();
        return checkDrawChoiceAnswer(answer);
    }

    public static boolean checkDrawChoiceAnswer(String answer) {
        if (answer.length() !=
                1 || (answer.equals("y") && answer.equals("n"))) {
            System.out.println("y또는 n을 입력해주세요.");
            System.exit(1);
        }
        return answer.equals("y");
    }

    public static void printFirstDrawResult(ArrayList<String> names) {
        String result = String.join(",", names);

        System.out.println("\n딜러와 " + result + "에게 2장의 카드를 나누었습니다.");
        dealer.printNameAndCards();
        for (Player player : players) {
            player.printNameAndCards();
        }
    }

    public static void checkDeckEmpty(Card card) {
        if (card == null) {
            System.out.println("더이상 card 가 남아있지 않습니다.");
            System.exit(1);
        }
    }

    public static ArrayList<Double> takePlayerBetting(ArrayList<String> names) {
        ArrayList<Double> betting = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        for (int i = 0; i < names.size(); i++) {
            System.out.println("\n" + names.get(i) + "의 배팅 금액은?");
            betting.add(s.nextDouble());
        }
        return betting;
    }

    public static void checkInput(ArrayList<String> names,
                                  ArrayList<Double> betting) {
        if (names.size() != betting.size()) {
            System.out.println("이릉과 배팅의 갯수가 맞지 않습니다");
            System.exit(1);
        }
        if (!checkInputName(names) || !checkInputBetting(betting)) {
            System.out.println("사용자 입력이 잘못되었습니다. 게임을 종료합니다.");
            System.exit(1);
        }

    }

    public static boolean checkInputName(ArrayList<String> names) {
        int numOfName = names.size();

        names.removeIf(i -> i.length() < 1 || i.length() > 5);
        if (numOfName == 0 || numOfName != names.size()) {
            System.out.println("이름 입력 형식에 맞지 않습니다. 이름의 길이는 1글자 이상"
                    + " 다섯글자 미만이어야 합니다.");
            return false;
        }
        return true;
    }

    public static boolean checkInputBetting(ArrayList<Double> betting) {
        int numOfBetting = betting.size();

        betting.removeIf(i -> i < 1);
        if (numOfBetting == 0 || numOfBetting != betting.size()) {
            System.out.println("배팅 입력 형식에 맞지 않습니다. 배팅금액은 1원 이상이어야" +
                    " 합니다.");
            return false;
        }
        return true;
    }

    public static ArrayList<String> takePlayerName() {
        Scanner s = new Scanner(System.in);
        String inputName;

        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        inputName = s.nextLine();
        return splitByComma(inputName);
    }

    public static ArrayList<String> splitByComma(String inputName) {
        ArrayList<String> names = new ArrayList<>();

        for (String name : inputName.split(",")) {
            names.add(name);
        }
        return names;
    }


}
