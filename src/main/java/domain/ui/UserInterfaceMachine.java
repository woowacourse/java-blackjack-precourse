package domain.ui;

import domain.user.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceMachine {
    private Scanner scanner = new Scanner(System.in);

    public List<String> scanNames() {
        final String StringGetNamesHeader = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
        ArrayList<String> names;

        System.out.println(StringGetNamesHeader);
        names = new ArrayList<String>(Arrays.asList(scanner.nextLine().split(",")));

        while (!isCorrectNames(names)) {
            System.out.println(names);
            printNameError();
            names = new ArrayList<String>(Arrays.asList(scanner.nextLine().split(",")));
        }

        return names;
    }

    private boolean isCorrectNames(List<String> names) {
        return true;
    }

    private boolean isCorrectName() {
        return true;
    }

    private void printNameError () {
        System.out.println("잘못된 이름 입력입니다.");
        System.out.println(" * 이름에는 특수문자를 사용할 수 없습니다.");
        System.out.println(" * 이름과 이름 사이를 \',\'로 분리해주세요.");
        System.out.println(" * ex)YelimKim,JongSeongLee,Rebeca");
    }

    public int scanBetAmountOfPlayer(String playerName) {
        return 0;
    }

    private boolean isCorrectBetAmount() {
        return true;
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
