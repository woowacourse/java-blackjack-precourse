package util;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackPrinterImpl implements BlackjackPrinter {

    private StringBuilder stringBuilder;
    public BlackjackPrinterImpl() {
        stringBuilder = new StringBuilder();
    }

    @Override
    public void printUserState(User user) {
        stringBuilder = buildUserState(user);
        System.out.println(stringBuilder);
        //todo: refac
        stringBuilder.delete(0, stringBuilder.length());
    }

    @Override
    public void printUserResult(User user) {
        stringBuilder = buildUserState(user);
        stringBuilder.append(" - 결과: ");
        stringBuilder.append(user.calculateScore());

        System.out.println(stringBuilder);
        stringBuilder.delete(0, stringBuilder.length());
    }

    //todo: fix bug - when dealer
    private StringBuilder buildUserState(User user) {
        stringBuilder.append(user);
        stringBuilder.append("카드: ");

        List<String> cardNames = user.getCards().stream().map(Card::toString).collect(Collectors.toList());
        if (user instanceof Dealer) {
            cardNames.retainAll(Collections.singletonList(cardNames.get(0)));
        }
        stringBuilder.append(String.join(", ", cardNames));
        return stringBuilder;
    }

    @Override
    public void printDealerHit(User user) {
        System.out.println(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.", user, BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER));
    }

    @Override
    public void printResult(Dealer dealer, List<Player> players) {
        System.out.println("##최종수익");
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
        System.out.println("딜러 및 참여자들에게 카드를 나눠줍니다.");
    }

    @Override
    public void printBreaktime() {
        System.out.println();
    }

    @Override
    public void printError(RuntimeException e) {
        System.out.println(String.format("다음과 같은 오류가 있습니다: %s", e.getMessage()));
    }
}
