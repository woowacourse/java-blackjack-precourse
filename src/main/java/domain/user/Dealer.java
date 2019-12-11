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

    /**
     * 처음 카드 2장을 나눠주는 메소드
     */
    public void giveCardsFirst(CardDeck cardDeck, List<Player> players) {
        giveCardToAll(cardDeck, players);
        giveCardToAll(cardDeck, players);
    }

    /**
     * 처음 2장을 받은 뒤, 블랙잭이 있나 확인하는 메소드
     *
     * @param players
     * @return 블랙잭이 있으면 true, 없으면 false
     */
    private Boolean checkIfBlackJack(List<Player> players) {
        /* 블랙잭이 있나 확인하면서, 블랙잭인 Player 또는 Dealer는 winners List에 저장 */

        List<BlackJackPlayer> winners = new ArrayList<>();
        Boolean ifPlayerBlackJack = ifOneOfPlayerBlackJack(players, winners);
        Boolean ifDealerBlackJack = addToWinnerIfBlackJack(winners);

        return terminateGameWithFirstBlackJack(players, winners, ifPlayerBlackJack, ifDealerBlackJack);
    }

    private void giveCardToAll(CardDeck cardDeck, List<Player> players) {
        for (Player player : players) {
            giveCard(cardDeck, player);
        }
        giveCard(cardDeck, this);
    }

    private void giveCard(CardDeck cardDeck, BlackJackPlayer player) {
        player.addCard(cardDeck.drawCard());
    }

    private Boolean ifOneOfPlayerBlackJack(List<Player> players, List<BlackJackPlayer> winners) {
        Boolean ifPlayerBlackJack = false;

        for (Player player : players) {
            ifPlayerBlackJack = player.addToWinnerIfBlackJack(winners) || ifPlayerBlackJack;
        }
        return ifPlayerBlackJack;
    }

    /**
     * 첫 패에 블랙잭이 잡혔을 경우, 나눠서 처리하는 메소드
     */
    private Boolean terminateGameWithFirstBlackJack(List<Player> players, List<BlackJackPlayer> winners,
                                                    Boolean ifPlayerBlackJack, Boolean ifDealerBlackJack) {
        if (ifDealerBlackJack && ifPlayerBlackJack) {
            // 처리
            return true;
        }
        if (ifDealerBlackJack) {
            // 처리
        }
        if (ifPlayerBlackJack) {
            // 처리
        }
        return ifDealerBlackJack || ifPlayerBlackJack;
    }
}
