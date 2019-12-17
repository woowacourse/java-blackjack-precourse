package domain.Game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Output {
    private final Dealer dealer;

    public Output(Dealer dealer) {
        this.dealer = dealer;
    }

    public void startCardState(List<Player> players) {
        String playerNames = players.stream().map(player -> String.valueOf(player.getName())).collect(Collectors.joining(", "));
        System.out.println("딜러와 " + playerNames + "에게 2장의 카드를 나누었습니다.");
        System.out.println(dealer.cardsToString());
        players.stream().forEach(player -> System.out.println(player.cardsToString()));
    }

    public boolean dealerAddCardDraw() {
        if (dealer.addCardDraw()) {
            System.out.println("딜러는 16이하라 카드를 더 받았습니다.");
            return true;
        }
        return false;
    }

    public void finalCardResult(List<Player> players) {
        System.out.println(dealer.cardsToString() + " - 결과 : " + dealer.scoreCalculator());
        players.stream().forEach(player -> System.out.println(player.cardsToString() + "- 결과 : " + player.scoreCalculator()));
    }

    public void bettingMoneyResult(Map<String, Double> bettingMoneyMap) {
        System.out.println("##최종수익");
        Iterator<String> bettingMoneyMapKeys = bettingMoneyMap.keySet().iterator();
        while (bettingMoneyMapKeys.hasNext()){
            String bettingMoneyMapKey = bettingMoneyMapKeys.next();
            System.out.println(bettingMoneyMapKey + " : " + bettingMoneyMap.get(bettingMoneyMapKey));
        }
    }
}
