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

    public void printBettingMoneyInput(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
    }

    public void printPlayersAndDealerCards(Dealer dealer, List<Player> players) {
        this.printAlertGetCards(players);
        this.printDealerCards(dealer);
        this.printPlayersCards(players);
    }

    private void printAlertGetCards(List<Player> players) {
        System.out.print("딜러와");
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
        System.out.println();
    }

    public void printPlayerCards(Player player) {
        System.out.print(""+player.getName()+"카드: ");
        List<String> stringCards = new ArrayList<>();
        for (Card card: player.getCards()) {
            stringCards.add(card.getSymbolName() + card.getTypeName());
        }
        System.out.println(String.join(", ", stringCards));
    }
}
