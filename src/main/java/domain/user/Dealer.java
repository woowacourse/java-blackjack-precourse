package domain.user;

import domain.card.CardDeck;
import domain.system.IO;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends BlackJackPlayer {
    public Dealer() {
    }

    /**
     * 이름을 입력받고, 이름 별로 배팅금액을 입력받아서 Player 객체를 생성하는 메소드
     *
     * @return 생성된 Player 객체의 List
     */
    public List<Player> makePlayers() {
        String[] playersName = IO.getPlayersNames().split(",");
        List<Player> players = new ArrayList<>();

        for (String name : playersName) {
            int bettingMoney = IO.getPlayerBettingMoney(name);
            players.add(new Player(name, bettingMoney));
        }
        return players;
    }

    public void giveCard(CardDeck cardDeck, BlackJackPlayer player) {
        player.addCard(cardDeck.drawCard());
    }
}
