package com.jenny.blackjack;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /*
     * 블랙잭 게임 실행 메서드
     * 1. init : 사용자를 입력받고 배팅금액을 설정하는 단계
     * 2. play : Hit or Stay를 결정하는 단계
     * 3. result : 결과를 계산하는 단계
     */
    public static void main(String[] args) {
        InitialSetting init = new InitialSetting();
        Play play = new Play();
        Result result = new Result();
        List<Player> players = new ArrayList<>();
        Dealer dealer = new Dealer();

        init.askUserName();
        init.askBetMoney(players);

        play.distributeInitialCards(dealer, players);
        play.showAllCardStatus(dealer, players);
        play.askAddCard(dealer, players);

        result.showResultValue(dealer, players);
        result.showResultMoney(dealer, players);
    }
}
