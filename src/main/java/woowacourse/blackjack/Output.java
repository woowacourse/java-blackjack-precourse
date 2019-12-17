package woowacourse.blackjack;

import domain.card.Card;
import domain.user.Player;
import domain.user.Dealer;

import java.util.ArrayList;
import java.util.List;

public class Output {
    public void printNamesInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printNamesInputAgain() {
        System.out.println("영어, 숫자만 가능하며 공백없이 쉽표로 분리되고 공백이 필료한 경우는 _ 나 - 를 사용하십시오.");
    }

    public void printNamesLengthInputAgain() {
        System.out.println("이름은 16자까지 가능합니다.");
    }

    public void printNameListLengthInputAgain() {
        System.out.println("인원은 최대 8명까지 가능합니다.");
    }

    public void printBettingMoneyInput(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
    }

    public void printBettingMoneyInputAgain() {
        System.out.println("1 이상의 숫자만 가능합니다.");
    }

    public void printPlayersAndDealerCards(Dealer dealer, List<Player> players) {
        this.printAlertGetCards(players);
        this.printDealerCards(dealer);
        this.printPlayersCards(players);
        System.out.println();
    }

    private void printAlertGetCards(List<Player> players) {
        System.out.print("딜러와 ");
        List<String> stringNames = new ArrayList<>();
        for (Player player: players) {
            stringNames.add(player.getName());
        }
        System.out.print(String.join(", ", stringNames));
        System.out.println("에게 2장의 카드를 나누었습니다.");
    }

    private void printDealerCards(Dealer dealer) {
        System.out.print("딜러: ");
        System.out.println(""+dealer.getCards().get(0).getSymbolName()+""
                            + ""+dealer.getCards().get(0).getTypeName()+"");
    }

    private void printPlayersCards(List<Player> players) {
        for (Player player: players) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.print(player.getName() + "카드: ");
        List<String> stringCards = new ArrayList<>();
        for (Card card: player.getCards()) {
            stringCards.add(card.getSymbolName() + card.getTypeName());
        }
        System.out.println(String.join(", ", stringCards));
    }

    public boolean isBlackJack(Dealer dealer, List<Player> players) {
        if (this.isDealerBlackJack(dealer)) {
            System.out.println("딜러 블랙잭!");
            System.out.println();
            return true;
        }
        return false;
    }

    private boolean isDealerBlackJack(Dealer dealer) {
        return dealer.isBlackJack();
    }

    private boolean isPlayersBlackJack(List<Player> players) {
        int amount = players.size();
        int i = 0;
        while (i < amount && !players.get(i).isBlackJack()) {
            i++;
        }
        return i < amount;
    }

    public void printBlackJack(Dealer dealer, List<Player> players) {
        System.out.println("## 최종 수익");
        Calculator calculator = new Calculator();
        calculator.setWhenBlackJack(dealer, players);
        this.printDealerAndPlayersRevenue(calculator, players);
    }

    public void printYesOrNo(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printYesOrNoAgain() {
        System.out.println("y or n (대문자 가능)로만 대답해주십시오.");
    }

    public void printAddDealerCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printSumScore(Dealer dealer, List<Player> players) {
        this.printDealerSumScore(dealer);
        this.printPlayersSumScore(players);
        System.out.println();
    }

    private void printDealerSumScore(Dealer dealer) {
        System.out.print("딜러 카드: ");
        List<String> stringCards = new ArrayList<>();
        for (Card card: dealer.getCards()) {
            stringCards.add(card.getSymbolName() + card.getTypeName());
        }
        System.out.print(String.join(", ", stringCards));
        System.out.println(" - 결과: " + dealer.getSumScore());
    }

    private void printPlayersSumScore(List<Player> players) {
        for (Player player: players) {
            this.printPlayerSumScore(player);
        }
    }

    private void printPlayerSumScore(Player player) {
        System.out.print(player.getName() + "카드: ");
        List<String> stringCards = new ArrayList<>();
        for (Card card: player.getCards()) {
            stringCards.add(card.getSymbolName() + card.getTypeName());
        }
        System.out.print(String.join(", ", stringCards));
        System.out.println(" - 결과: " + player.getSumScore());
    }

    public void printFinalRevenue(Dealer dealer, List<Player> players) {
        System.out.println("## 최종 수익");
        this.calculatorCardScore(dealer, players);
    }

    private void calculatorCardScore(Dealer dealer, List<Player> players) {
        Calculator calculator = new Calculator();
        calculator.setRevenue(dealer, players);
        this.printDealerAndPlayersRevenue(calculator, players);
    }

    private void printDealerAndPlayersRevenue(Calculator calculator, List<Player> players) {
        System.out.println("딜러: " + calculator.getDealerRevenue());
        for (Player player: players) {
            System.out.println(player.getName() + ": " + calculator.getPlayerRevenue(player));
        }
    }
}