package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends BlackJackPlayer {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    /**
     * 최종 수익을 계산하는 메소드
     *
     * @return 승리했다면 배팅 비율에 맞는 번 돈, 패배했다면 -1 * 배팅금액
     */
    public double calculateEarn(int winningScore, double rate) {
        if (getScoreOfCards() == BURST_SCORE) {     // 21을 넘긴 플레이어는 항상 잃음
            return -bettingMoney;
        }
        if (getScoreOfCards() >= winningScore) {
            return bettingMoney * rate - bettingMoney;
        }
        return -bettingMoney;
    }

    public String getName() {
        return this.name;
    }

    public Boolean canHaveMoreCard() {
        return getSumOfCards() <= BLACK_JACK_NUMBER;
    }

    @Override
    public String toString() {
        return name;
    }
}
