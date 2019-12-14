package domain.game;

import java.util.List;
import java.util.stream.Collectors;

import domain.user.Contender;

public class Discriminator {
    List<Contender> rankedPlayers;

    public Discriminator(List<Contender> contenders) {
        this.rankedPlayers = contenders
            .stream()
            .filter(Contender::notBusted)
            .sorted()
            .collect(Collectors.toList());
    }

    // 플레이어의 중도 탈락
    //   카드 합이 21 초과 시 즉시 탈락
    // 딜러의 중도 탈락
    //   탈락하지 않은 플레이어들은 베팅 금액을 돌려받음
    // 딜러의 블랙잭 (첫 페어 합 21)
    //   블랙잭인 플레이어는 베팅 금액을 돌려받음
    //   그 외 플레이어는 패배
    // 플레이어의 블랙잭 (첫 페어 합 21)
    //   베팅 금액의 1.5배를 받음
    //   그 외 블랙잭이 아닌 플레이어는 패배
    // 블랙잭이 없는 경우 카드 총합이 21에 가까운 순서로 1등을 선발
    //   딜러 1등: 모든 플레이어 패배
    //   플레이어 1등: 1등만 베팅 금액을 돌려받음
    //   무승부: 딜러와 점수가 같은 플레이어들은 베팅 금액을 돌려받음
}
