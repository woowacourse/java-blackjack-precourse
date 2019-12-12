package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

/**
 * BlackJack 클래스는 블랙잭 게임을 실행하고, 유저/딜러와 카드간의 연계, 배팅 등을 처리해주는 클래스이다.
 * <p>
 * 이 클래스는 싱글톤 기법으로 설계되었다.
 * 프로그램 설계 상 여러 블랙잭 게임이 동시에 실행될 수 없으므로,
 * 하나의 블랙잭 게임을 static으로 생성해 두고 이를 활용하게 설계하였다.
 * 본 프로젝트에는 동기화 이슈가 없지만, 추후 재사용하거나 참고할 떄를 대비하여
 * 별도의 Holder 클래스를 둬 멀티쓰레딩 환경에서 여러 개가 생성되지 않도록 하였다.
 *
 * @author kafka
 * @version 1.0
 */

public class BlackJack {
    private List<Player> playerList;
    private Dealer dealer;
    private List<Card> cardList;
    private int cardIterator;

    /**
     * 정적이고 보호되는 객체 BlackJackHolder는,
     * 프로그램 전체에 유일하게 존재함이 보장되어야 하는 블랙잭 인스턴스를 생성한다.
     */
    private static class BlackJackHolder {
        public static final BlackJack INSTANCE = new BlackJack();
    }

    /**
     * getInstace는 static 메서드로, 호출 시 미리 BlackJackHolder를 통해 생성된 인스턴스를 반환한다.
     *
     * @return 미리 생성된 유일한 BlackJack 인스턴스를 반환해준다.
     */
    public static BlackJack getInstance() {
        return BlackJackHolder.INSTANCE;
    }

    /**
     * initBlackJack은 블랙잭 수행을 위한 유저, 카드, 플레이어들의 생성과 초기화를 담당한다.
     */
    public void initBlackJack() {
        cardList = CardFactory.create();
        cardIterator = 0;
        dealer = new Dealer();
        playerList = createPlayerList();
    }

    /**
     * drawCard 메서드는 카드를 한 장 뽑아 반환하고, iterator 값을 올려준다.
     * 카드의 리스트는 생성시 셔플되므로, 이를 선형으로 순회하게 되면
     * 중복 없이 랜덤한 카드가 뽑히는 것을 보장할 수 있다.
     *
     * @return 뽑힌 카드를 반환해준다.
     * @throws AssertionError 뽑을 카드가 없는 경우, 논리적 에러를 생성한다.
     */
    public Card drawCard() {
        if (cardIterator >= cardList.size()) {
            System.out.print(Message.ERROR_CARD_EMPTY);
            throw new AssertionError();
        }
        return cardList.get(cardIterator++);
    }

    /**
     * createPlayerList는 플레이어 목록에 새로운 플레이어들을 생성해준다.
     * 이름의 목록을 받아 이름을 집어넣고, 각자의 배팅 금액도 집어넣는다.
     *
     * @return Player 객체 List를 반환한다.
     */
    public List<Player> createPlayerList() {
        List<Player> playerList = new ArrayList<Player>();
        List<String> nameList = getNameToInput();
        for (String name : nameList) {
            playerList.add(new Player(name, getBettingMoneyToInput(name)));
        }
        return playerList;
    }

    /**
     * getBettingMoneyToInput은 베팅할 금액을 입력받아 리턴하는 메서드이다.
     * 예외처리 : 잘못된 값이 들어오면 에러 메세지를 출력하고, 자기 자신을 리턴해준다.
     * 이를 통해 재귀적으로 올바른 값을 돌려준다.
     *
     * @return int형의 배팅 금액을 리턴해준다.
     */
    private int getBettingMoneyToInput(String name) {
        Scanner sc = new Scanner(System.in);
        int money;

        System.out.print(name + Message.BET_PLAYER);
        try {
            money = sc.nextInt();
            if (money <= 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.print(Message.ERROR_INPUT);
            return getBettingMoneyToInput(name);
        }
        return money;
    }

    /**
     * getNameToInput은 하나의 문자열 입력을 받아,
     * 그 문자열을 여러 개의 이름 블록으로 분리하여 리스트로 만든다.
     *
     * @return 이름의 목록을 String형 List로 리턴해준다.
     */
    private List<String> getNameToInput() {
        Scanner sc = new Scanner(System.in);
        List<String> nameList;

        System.out.println(Message.GET_NAME);
        try {
            nameList = Arrays.asList(sc.nextLine().split(","));
            if (findExceptionOnName(nameList)) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.print(Message.ERROR_INPUT);
            return getNameToInput();
        }
        return nameList;
    }

    /**
     * findExceptionOnName은 이름의 목록을 받아 그 중 예외처리될 사항을 검사한다.
     *
     * @param nameList : 이름의 목록을 담은 리스트이다.
     * @return : 만약 입력에 예외처리될 사항이 있다면 true를, 아니라면 false를 리턴해준다.
     */
    private boolean findExceptionOnName(List<String> nameList) {
        if (nameList.isEmpty()) {
            return true;
        }
        for (String i : nameList) {
            if (i.length() > 5 || i.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * firstDraw는 게임을 시작할 때 2장씩 유저들에게 주는 로직을 담당하는 메서드이다.
     * 딜러는 1장만 공개하고, 나머지는 2장의 카드 모두 출력한다.
     * 마지막엔 공백을 넣어준다.
     */
    public void firstDraw() {
        dealer.addCard(drawCard());
        System.out.println(dealer.getOneCardString());
        dealer.addCard(drawCard());
        for(Player player : playerList) {
            player.addCard(drawCard());
            player.addCard(drawCard());
            System.out.println(player.getCardString());
        }
        System.out.print("\n");
    }
    public void printResultScore() {
        System.out.println(dealer.getCardString() + dealer.getScoreString());
        for(Player player : playerList) {
            System.out.println(player.getCardString() + player.getScoreString());
        }
    }
    public void printResultMoney() {
        System.out.println(Message.PRINT_RESULT);
        System.out.println(dealer.getMoneyString());
        for(Player player : playerList) {
            System.out.println(player.getMoneyString());
        }
    }
    public void playGame() {
        initBlackJack();
        firstDraw();
        printResultScore();
        printResultMoney();
    }
}
