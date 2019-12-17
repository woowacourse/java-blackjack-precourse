/*
 * @(#)BlackjackPrinterImpl.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class BlackjackPrinterImpl implements BlackjackPrinter {

    private StringBuilder stringBuilder;

    public BlackjackPrinterImpl() {
        stringBuilder = new StringBuilder();
    }

    @Override
    public void printUserState(User user) {
        stringBuilder = buildUserState(user);
        printStringBuilder(stringBuilder);
    }

    @Override
    public void printUserResult(User user) {
        stringBuilder = buildUserResult(user);
        stringBuilder.append(" - 결과: ");
        int score = user.calculateScore();
        if (BlackjackConfig.BLACKJACK < score) {
            stringBuilder.append(String.format("버스트(%d)", score));
            printStringBuilder(stringBuilder);
            return;
        }
        stringBuilder.append(score);
        printStringBuilder(stringBuilder);
    }

    @Override
    public void printDealerHit(User user) {
        System.out.println(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.",
                user, BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER));
    }

    @Override
    public void printRequestForNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    @Override
    public void printRequestForMoney(String name) {
        System.out.println(String.format("%s의 배팅 금액은?", name));
    }

    @Override
    public void printRequestForHit(User user) {
        System.out.println(String.format("%s는 한 장의 카드를 더 받겠습니까?", user));
    }

    @Override
    public void printStart(Dealer dealer, List<Player> players) {
        List<String> playerNames = players.stream().map(Player::toString).collect(Collectors.toList());
        System.out.println(String.format("%s와 %s에게 %d장의 카드를 나누었습니다.", dealer, String.join(",", playerNames), BlackjackConfig.DEFAULT_CARD_NUMBER));
    }

    @Override
    public void printBreaktime() {
        System.out.println();
    }

    @Override
    public void printError(RuntimeException e) {
        System.out.println(String.format("다음과 같은 오류가 있습니다: %s", e.getMessage()));
    }

    @Override
    public void printProfit(User user) {
        System.out.println(String.format("%s: %.0f", user, user.getProfit()));
    }

    @Override
    public void printBurst(User user) {
        System.out.println(String.format("%s는 버스트하여, 더이상 카드를 받을 수 없습니다.", user));
    }

    @Override
    public void printProfitGuide() {
        System.out.println("## 최종수익");
    }

    private StringBuilder buildUserState(User user) {
        stringBuilder.append(user);
        stringBuilder.append("카드: ");

        List<String> cardNames = user.getCards().stream()
                .map(Card::toString).collect(Collectors.toList());
        if (user instanceof Dealer) {
            cardNames.retainAll(Collections.singletonList(cardNames.get(0)));
        }
        stringBuilder.append(String.join(", ", cardNames));
        return stringBuilder;
    }

    private StringBuilder buildUserResult(User user) {
        stringBuilder.append(user);
        stringBuilder.append("카드: ");
        List<String> cardNames = user.getCards().stream().map(Card::toString).collect(Collectors.toList());
        stringBuilder.append(String.join(", ", cardNames));
        return stringBuilder;
    }

    private void printStringBuilder(StringBuilder stringBuilder) {
        System.out.println(stringBuilder);
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();
    }
}
