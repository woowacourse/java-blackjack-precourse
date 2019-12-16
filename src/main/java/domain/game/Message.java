/*
 * Message.java
 *
 * version 1.0
 *
 * 20191215
 *
 * copyright by jiwonSong(s26788761@naver.com)
 *
 */

package domain.game;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Message {
    public static final String DEALER = "딜러";
    public static final String CARD = "카드: ";
    public static final String RESULT = " - 결과: ";
    public static final String AND = "와";
    public static final String FINAL_PROFIT = "## 최종 수익";
    public static final String COLON = ": ";
    public static final String NEW_LINE = "\n";

    public static final String MESSAGE_ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String MESSAGE_ASK_PLAYER_BATTING_AMOUNT = "의 배팅 금액은?";
    public static final String MESSAGE_DEALL_TWO_CARDS_TO_PLAYER = "에게 2장의 카드를 나누었습니다.";
    public static final String MESSAGE_ASK_PLAYER_WANT_MORE_CARD = "은/는 한장의 카드를 더 받겠습니까?(예는 " + InputManager.YES + ", 아니오는 " + InputManager.NO + ")";
    public static final String MESSAGE_DEALER_GET_MORE_CARD = DEALER + "는 " + "16" + "이하라 한장의 카드를 더 받았습니다.";
    public static final String MESSAGE_DEALER_GET_NO_MORE_CARD = DEALER + "는 " + "17" + "이상이라 한장의 카드를 더 받지 않았습니다.";

    public static String makeMessageAskPlayerNames() {
        return MESSAGE_ASK_PLAYER_NAMES;
    }

    public static String makeMessageAskPlayerBattingAmout(String playerName) {
        return playerName + MESSAGE_ASK_PLAYER_BATTING_AMOUNT;
    }

    public static String makeMessageDealTwoCardsToPlayer(ArrayList<Player> players) {
        List<String> playerNameList = new ArrayList<>();
        for (Player player : players) {
            playerNameList.add(player.getName());
        }
        return DEALER + AND + String.join(",", playerNameList) + MESSAGE_DEALL_TWO_CARDS_TO_PLAYER;
    }

    public static String makeMessageDealerSmallestState(Dealer dealer) {
        Card smallestCard = dealer.getSmallestCard();
        return DEALER + CARD + smallestCard.toString();
    }

    public static String makeMessageDealerState(Dealer dealer) {
        return DEALER + CARD + dealer.getCardListString();
    }

    public static String makeMessagePlayerState(Player player) {
        return player.getName() + CARD + player.getCardListString();
    }

    public static String makeMessagePlayerWantMoreCard(Player player) {
        return player.getName() + MESSAGE_ASK_PLAYER_WANT_MORE_CARD;
    }

    public static String makeMessageDealerGetMoreCard() {
        return MESSAGE_DEALER_GET_MORE_CARD;
    }

    public static String makeMessageDealerGetNoMoreCard() {
        return MESSAGE_DEALER_GET_NO_MORE_CARD;
    }

    public static String makeMessageDealerResult(Dealer dealer) {
        return makeMessageDealerState(dealer) + RESULT + dealer.getScore();
    }

    public static String makeMessagePlayerResult(Player player) {
        return makeMessagePlayerState(player) + RESULT + player.getScore();
    }

//    GameManager 클래스 구현 전이라 주석 처리
//    public static String makeMessageFinalProfit(GameManager gameManager) {
//        Dealer dealer = gameManager.getDealer();
//        ArrayList<Player> players = gameManager.getPlayers();
//        int maxValue = gameManager.getMaxValue();
//        double battingRatio = gameManager.getBattingRatio();
//
//        String finalProfitMessage = FINAL_PROFIT;
//        finalProfitMessage += DEALER + COLON + dealer.getEarnMoney(players, maxValue, battingRatio) + NEW_LINE;
//        for (Player player : players) {
//            finalProfitMessage += player.getName() + COLON + player.getEarnMoney(maxValue, battingRatio) + NEW_LINE;
//        }
//        return finalProfitMessage;
//    }
}
