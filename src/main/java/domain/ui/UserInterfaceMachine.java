package domain.ui;

import domain.card.Card;
import domain.card.Type;
import domain.user.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceMachine {
    private Scanner scanner = new Scanner(System.in);

    /**
     * 이름 입력받기
     */
    public List<String> scanNames() {
        final String NAME_INPUT_HEADER = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
        ArrayList<String> names;

        System.out.println(NAME_INPUT_HEADER);
        names = new ArrayList<String>(Arrays.asList(scanner.nextLine().split(",")));

        while (!isCorrectNames(names)) {
            printNameError();
            System.out.println(NAME_INPUT_HEADER);
            names = new ArrayList<String>(Arrays.asList(scanner.nextLine().split(",")));
        }

        return names;
    }

    private boolean isCorrectNames(List<String> names) {
        boolean ret = true;

        if (names.size() < 1) {
            return false;
        }

        for (int idx = 0; idx < names.size() && ret; idx++) {
            /* 이름들을 각각 확인하여, 아직 잘못된 이름이 발견되지 않았으면 실행됨 */
            ret = isCorrectName(names.get(idx));
        }

        return ret;
    }

    private boolean isCorrectName(String name) {

        if (isTooShort(name)) {
            return false;
        }

        if (areThereSpecialCharacters(name)) {
            return false;
        }

        return true;
    }

    private boolean isTooShort(String name) {

        if (name.length() > 0) {
            return false;
        }

        return true;
    }

    private boolean areThereSpecialCharacters(String name) {

        if (name.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
            /* 이름에 특수문자가 없으면 실행됨 */
            return false;
        }

        return true;
    }

    private void printNameError () {
        System.out.println("잘못된 이름 입력입니다.");
        System.out.println(" * 이름에는 특수문자를 사용할 수 없습니다.");
        System.out.println(" * 이름과 이름 사이를 \',\'로 분리해주세요.");
        System.out.println(" * ex)YelimKim,JongSeongLee,Rebeca");
    }

    /**
     * 배팅 금액 입력받기
     */
    public int scanBetAmountOfPlayer(String playerName) {
        final String BET_AMOUNT_INPUT_HEADER = playerName + "의 배팅 금액은?";
        int betAmount = 0;

        System.out.println(BET_AMOUNT_INPUT_HEADER);
        betAmount = scanBetAmountOnlyOnce();

        while (!isCorrectBetAmount(betAmount)) {
            printBetAmountError();
            System.out.println(BET_AMOUNT_INPUT_HEADER);
            scanner.nextLine();                   /* 숫자를 입력받기 전에 버퍼 비우기 */
            betAmount = scanBetAmountOnlyOnce();
        }

        return betAmount;
    }

    private void printBetAmountError() {
        System.out.println("잘못된 배팅금액 입력입니다.");
        System.out.println(" * 배팅 금액은 0보다 큰 숫자만 가능합니다.");
    }

    /**
     * 배팅금액을 한번만 입력받는데, 잘못된 입력이면 음수를 return 하고
     * 올바른 입력이면 입력받은 값을 return 한다.
     */
    private int scanBetAmountOnlyOnce() {
        int ret = 0;
        try {
            ret = scanner.nextInt();
            return ret;
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    private boolean isCorrectBetAmount(int betAmount) {

        if (betAmount <= 0) {
            return false;
        }

        return true;
    }

    /**
     * 플레이어들이 가지고 있는 카드 전체 출력하기
     */
    public void printPlayersCards(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + explainPlayerCards(player));
        }
    }

    private String explainPlayerCards(User player) {
        StringBuilder ret = new StringBuilder();
        List<Card> playerCards = player.openAllCards();

        for (Card card : playerCards) {
            ret.append(explainCard(card) + ", ");
        }

        ret.delete(ret.length() - 2, ret.length());       /* 마지막 콤마+공백(, ) 제거 */

        return ret.toString();
    }

    private String explainCard(Card card) {
        return explainSymbol(card) + explainType(card);
    }

    private String explainSymbol(Card card) {
        int score = card.getSymbol().getScore();

        if (score == 10 || score == 1) {
            return card.getSymbol().toString().substring(0, 1);
        }

        return score + "";
    }

    private String explainType(Card card) {
        return translateTypeToKorean(card.getType());
    }

    private String translateTypeToKorean(Type cardType) {

        if (cardType.equals(Type.SPADE)) {
            return "스페이드";
        }

        if (cardType.equals(Type.DIAMOND)) {
            return "다이아몬드";
        }

        if (cardType.equals(Type.HEART)) {
            return "하트";
        }

        return "클로버";
    }

    /**
     * 딜러가 받은 첫번째 카드 출력하기
     */
    public void printDealerFirstCard(Dealer dealer) {

    }

    public String scanWhetherPlayerReceiveCard() {
        return "";
    }

    private boolean isCorrectWhetherPlayerReceiveCard() {
        return true;
    }

    public void printUsersCardsResults(Dealer dealer, List<Player> players) {

    }

    private void printUserCardsResult(User user) {

    }

    public void printUsersFinalRevenues(Dealer dealer, List<Player> players) {

    }

    private void printUserFinalRevenue(User user) {

    }
}
