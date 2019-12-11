package domain.user;

import domain.card.CardDeck;
import domain.system.IO;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends BlackJackPlayer {
    private static final double TIE_RATE = 1;

    private static final double FIRST_BLACK_JACK_RATE = 1.5;

    private static final double WINNERS_RATE = 2;

    public Dealer() {
    }

    public String getName() {
        return "딜러";
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
        IO.printGiveCardsFirst(players, this);
        checkIfBlackJack(players);
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

    /**
     * 처음 2장을 받은 뒤, 블랙잭이 있나 확인하는 메소드
     *
     * @param players
     * @return 블랙잭이 있으면 true, 없으면 false
     */
    private Boolean checkIfBlackJack(List<Player> players) {
        Boolean ifPlayerBlackJack = false;
        Boolean ifDealerBlackJack = ifHaveWinnerScore(BLACK_JACK_NUMBER);

        for (Player player : players) {
            ifPlayerBlackJack = player.ifHaveWinnerScore(BLACK_JACK_NUMBER) || ifPlayerBlackJack;
        }
        terminateGameWithFirstBlackJack(players, ifPlayerBlackJack, ifDealerBlackJack);
        return ifDealerBlackJack || ifPlayerBlackJack;
    }

    /**
     * 첫 패에 블랙잭이 잡혔을 경우, 나눠서 처리하는 메소드
     */
    private void terminateGameWithFirstBlackJack(List<Player> players,
                                                 Boolean ifPlayerBlackJack, Boolean ifDealerBlackJack) {
        if (ifDealerBlackJack && ifPlayerBlackJack) {
            distributeMoney(players, TIE_RATE, BLACK_JACK_NUMBER);
            return;
        }
        if (ifDealerBlackJack || ifPlayerBlackJack) {       // 어차피 딜러만 이긴 경우에는 플레이어들은 아무도 돈을 못타감
            distributeMoney(players, FIRST_BLACK_JACK_RATE, BLACK_JACK_NUMBER);
        }
    }

    private void distributeMoney(List<Player> players, double rate, int winningScore) {
        double dealerEarn = 0;

        for (Player player : players) {
            dealerEarn += -player.calculateEarn(winningScore, rate);    // 딜러가 번 돈과 플레이어가 잃은 돈이 같음
        }
    }
}
