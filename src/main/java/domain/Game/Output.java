package domain.Game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Output {
    private final Dealer dealer = new Dealer();
    private final String DEALER_NAME = "딜러";


    public void StartCardState(List<Player> players){
        String playerNames = players.stream().map(player -> String.valueOf(player.getName())).collect(Collectors.joining(", "));

        System.out.println(DEALER_NAME + "와 " + playerNames +"에게 2장의 카드를 나누었습니다.");
        System.out.println(dealer.cardsToString());
        players.stream().forEach(player -> System.out.println(player.cardsToString()));
    }
}
