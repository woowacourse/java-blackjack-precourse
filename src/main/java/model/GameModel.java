package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

/**
 * @author KIMSIYOUNG
 * @apiNote 게임 처리를 담당하는 모델로, 카드 분배 및 결과 값 계산 등의 기능이 있습니다.
 * @since 2019-12-13
 */
public class GameModel {
    private static final double WIN_RATE = 1;
    private static final double DRAW_RATE = 0;
    private static final double LOSE_RATE = -1;
    private static final double FIRST_BLACKJACK_PROFIT = 0.5;
    private static final double ZERO = 0;
    private static final int SECOND = 2;
    private static final Integer TWENTY = 20;

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private static final Deck deck = Deck.getInstance();

    public GameModel(List<String> userNames, List<Double> userBettingMoneys) {
        for (int i = 0; i < userNames.size(); i++) {
            players.add(new Player(userNames.get(i), userBettingMoneys.get(i)));
        }
    }

    public void play() throws IOException {
        giveTwoCardsToEveryOne();
        checkPlayerFirstBlackJack();
        giveOneMoreCardToPlayers();
        checkOneMoreCardToDealer();
        showResultOfEveryCards();
        for (Player player : players) {
            setPlayerProfit(player, dealer);
        }
        setProfitOfDealer();
    }

    private void giveTwoCardsToEveryOne() {
        giveTwoCardsToPlayer();
        giveTwoCardsToDealer();
    }

    private void giveTwoCardsToPlayer() {
        for (Player player : players) {
            giveEachTwoCards(player);
            OutputView.printCards(player);
        }
    }

    private void giveTwoCardsToDealer() {
        giveEachTwoCards(dealer);
        OutputView.printDealerOneCard(dealer);
    }

    private void giveEachTwoCards(Gamer gamer) {
        for (int i = 0; i < SECOND; i++) {
            gamer.addCard(deck.getRandomCard());
        }
    }

    private void checkPlayerFirstBlackJack() {
        for (Player player : players) {
            checkFirstBlackjack(player);
        }
    }

    private void checkFirstBlackjack(Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack())
            player.multiplyProfit(FIRST_BLACKJACK_PROFIT);
    }

    private void giveOneMoreCardToPlayers() throws IOException {
        for (Player player : players) {
            checkCanHaveMoreAndGive(player);
        }
    }

    private boolean checkCanHaveMoreAndGive(Player player) throws IOException {
        if (canHaveMore(player) && wantOneMore(player)) {
            player.addCard(deck.getRandomCard());
            OutputView.printCards(player);
            return checkCanHaveMoreAndGive(player);
        }
        return false;
    }

    private boolean canHaveMore(Player player) {
        return player.sumOfCard() <= TWENTY;
    }

    private boolean wantOneMore(Player player) throws IOException {
        InputView inputView = new InputView();
        return inputView.wantOneMore(player);
    }

    private void checkOneMoreCardToDealer() {
        if (dealer.shouldHaveOneMoreCard()) {
            dealer.addCard(deck.getRandomCard());
            OutputView.printDealerGetOneMore();
        }
    }

    private void showResultOfEveryCards() {
        for (Player player : players) {
            OutputView.printPlayerCardsAndResult(player);
        }
        OutputView.printDealerCardsAndResult(dealer);
    }

    private boolean setPlayerProfit(Player player, Dealer dealer) {
        playerIsAlreadyBlackJack(player, dealer);  // 플레이어가 처음 받은 2장의 카드가 블랙잭인 경우
        if (playerWin(player, dealer)) {  //  플레이어가 이긴 경우
            return true;
        }
        if (playerDraw(player, dealer)) {  // 플레이어와 딜러가 비긴경우
            return true;
        }
        playerLose(player);  //  플레이어가 진 경우
        return false;
    }

    private void playerIsAlreadyBlackJack(Player player, Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack())
            OutputView.printPlayerProfit(player);
    }

    private void playerLose(Player player) {
        if (!player.isBlackJack()) {
            player.multiplyProfit(LOSE_RATE);
            OutputView.printPlayerProfit(player);
        }
    }

    private boolean playerDraw(Player player, Dealer dealer) {
        if (player.isDrawGame(dealer) && !player.isBurst() && !dealer.isBurst()) {
            player.multiplyProfit(DRAW_RATE);
            OutputView.printPlayerProfit(player);
            return true;
        }
        return false;
    }

    private boolean playerWin(Player player, Dealer dealer) {
        if (player.isWinGame(dealer) && !player.isBlackJack()) {
            player.multiplyProfit(WIN_RATE);
            OutputView.printPlayerProfit(player);
            return true;
        }
        return false;
    }

    private void setProfitOfDealer() {
        double dealerProfit = ZERO;
        for (Player player : players) {
            dealerProfit += dealer.calculateProfit(player);
        }
        OutputView.printDealer(dealerProfit);
    }
}
