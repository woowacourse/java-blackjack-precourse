package domain.user;

import domain.card.CardDeck;
import domain.system.IO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends BlackJackPlayer {
    private static final double TIE_RATE = 1;

    private static final double FIRST_BLACK_JACK_RATE = 1.5;

    private static final double WINNERS_RATE = 2;

    private static final int DEALER_MAX_THRESHOLD = 16;

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
    public Boolean giveCardsFirst(CardDeck cardDeck, List<Player> players) {
        giveCardToAll(cardDeck, players);
        giveCardToAll(cardDeck, players);
        IO.printGiveCardsFirst(players, this);
        return checkIfBlackJack(players);
    }

    /**
     * 플레이어들과 딜러가 각각 카드를 한장씩 더 받을지 결정하고 처리하는 메소드
     */
    public void haveMoreCards(List<Player> players, CardDeck cardDeck) {
        for (Player player : players) {
            haveMoreCardPlayer(cardDeck, player);
        }
        haveMoreCardDealer(cardDeck, this);
    }

    public void calculateFinalEarn(List<Player> players) {
        int winningScore = getWinningScore(players);

        IO.printPlayersCard(players, this);
        if (getSumOfCards() > BLACK_JACK_NUMBER) {      // 딜러가 21을 넘겼을 경우, 남아있는 모두가 승리
            distributeMoney(players, WINNERS_RATE, BURST_SCORE);
            return;
        }
        distributeMoney(players, WINNERS_RATE, winningScore);   // 승리한 사람은 배팅금액의 2배를 가져감
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
        Boolean ifDealerBlackJack = ifHaveBlackJack();

        for (Player player : players) {
            ifPlayerBlackJack = player.ifHaveBlackJack() || ifPlayerBlackJack;
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

    /**
     * Player가 더 카드를 받을 것인지 묻고, 더 주는 메소드
     */
    private void haveMoreCardPlayer(CardDeck cardDeck, Player player) {
        while (player.canHaveMoreCard() && IO.haveMoreCard(player)) {
            giveCard(cardDeck, player);
            IO.printPlayerCard(player);
        }
    }

    /**
     * 딜러는 16 이하일 경우 카드를 받고, 17 이상일 경우 그만둠
     */
    private void haveMoreCardDealer(CardDeck cardDeck, Dealer dealer) {
        while (dealer.canHaveMoreCard()) {
            giveCard(cardDeck, dealer);
            IO.printDealerHaveMore();
        }
    }

    private Boolean canHaveMoreCard() {
        return getSumOfCards() <= DEALER_MAX_THRESHOLD;
    }

    private int getWinningScore(List<Player> players) {
        int winningScore = players.stream().max(Comparator.comparing(Player::getScoreOfCards)).get().getScoreOfCards();

        return Math.max(winningScore, getScoreOfCards());
    }

    private void distributeMoney(List<Player> players, double rate, int winningScore) {
        double dealerEarn = 0;

        IO.printFinalEarn();
        for (Player player : players) {
            double playerEarn = player.calculateEarn(winningScore, rate);
            dealerEarn += -playerEarn;          // 딜러가 번 돈과 플레이어가 잃은 돈이 같음
            IO.printEarn(player.toString(), playerEarn);
        }
        IO.printEarn(toString(), dealerEarn);
    }

    @Override
    public String toString() {
        return "딜러";
    }
}
