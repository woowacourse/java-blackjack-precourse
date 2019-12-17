package domain.user;

import domain.card.CardFactory;

/**
 * 본 프로그램이 실행되는 클래스
 */
public class BlackjackApp {
    
    private static final String MESSAGE_INDECATOR = "===== %s =====%n";
    private static final String DEAL_CARD = "Dealing 2 Cards Each";
    private static final String WHOS_CARD = "'s cards: ";
    private static final String DEBUGGING = "[Debugging Mode]";
    private static final String DEALER = "Dealer";
    private static final String WHOS_TURN = "'s Turn";
    private static final String RESULT = "Result";
    private static final String NET_INCOME = "Net Income";
    private static final String NET_INCOME_OF = "Net Income Of %s: %s%n";
    
    public static void main(String[] args) {
        EntirePlayer players = new EntirePlayer();
        Dealer dealer = new Dealer();
        new CardFactory();
        initialize2Cards(players, dealer);
        printInitialCards(players, dealer);
        activateTurn(players, dealer);
        printResult(players, dealer);
        printNetIncome(players, dealer);
    }
    
    /** 모든 플레이어와 딜러에게 카드를 두 장씩 분배한다. */
    private static void initialize2Cards(EntirePlayer players, Dealer dealer) {
        System.out.printf(MESSAGE_INDECATOR, DEAL_CARD);
        for (Player eachPlayer: players.getList()) {
            eachPlayer.addCard(CardFactory.shift());
            eachPlayer.addCard(CardFactory.shift());
        }
        dealer.addCard(CardFactory.shift());
        dealer.addCard(CardFactory.shift());
    }
    
    /** 초기 분배한 카드를 보여준다 */
    private static void printInitialCards(EntirePlayer players, Dealer dealer) {
        players.getList().stream().map(Player::toStringStatus)
                        .forEach(System.out::println);
        System.out.println(DEALER + WHOS_CARD + dealer.getCards().get(0).toString()); // actual code
//        System.out.println(dealer.toStringStatus() + DEBUGGING); // debugging code
    }
    
    /** 턴 시작한다는 메세지 출력과 함께 각 플레이어의 턴을 진행한다. */
    private static void activateTurn(EntirePlayer players, Dealer dealer) {
        for (Player eachPlayer: players.getList()) {
            System.out.printf(MESSAGE_INDECATOR, eachPlayer.getName() + WHOS_TURN);
            eachPlayer.activateTurn();
        }
        System.out.printf(MESSAGE_INDECATOR, DEALER + WHOS_TURN);
        dealer.activateTurn();
    }
    
    private static void printResult(EntirePlayer players, Dealer dealer) {
        System.out.printf(MESSAGE_INDECATOR, RESULT);
        players.getList().stream().map(Player::toStringStatus)
                .forEach(System.out::println);
        System.out.println(dealer.toStringStatus());
    }

    private static void printNetIncome(EntirePlayer players, Dealer dealer) {
        System.out.printf(MESSAGE_INDECATOR, NET_INCOME);
        for (Player eachPlayer: players.getList()) {
            System.out.printf(NET_INCOME_OF, eachPlayer.getName(),
                            eachPlayer.getNetIncome(dealer));
        }
        System.out.printf(NET_INCOME_OF, DEALER,
                            dealer.getNetIncome(players.getList()));
    }
}